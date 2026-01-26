# Virtualisation - TP3

> [!TIP]
>
> Avant de commencer, il est vivement recommandé d’ajouter
> à la fin de votre fichier ~/.bashrc la ligne suivante:
> `./usr/share/bash-completion/bash_completion`
> et utiliser la touche **Tab** pendant l’édition de vos commandes.
> Même chose:
> `source <(kubectl completion bash)`
> et
> `source <(minikube completion bash)`

## 1. L’objet secret de Kubernetes

### 1.1. Déployer une application avec secret codé

Vous pouvez créer un secret dans un fichier d’abord, au format
yaml, puis créer cet objet.

Le secret contient deux tables de hachage: `data` et `stringData`.

Le champ `data` est utilisé pour stocker des données
arbitraires, encodées en `base64`.

Le champ `stringData` est fourni pour plus de commodité et vous
permet de fournir des données secrètes sous forme de chaînes
non codées.

### 1.2. Création manuelle d’un secret

Créez un fichier secret.yaml dans votre répertoire.

> Voir [secret.yaml](secret.yaml)

Cherchez la signification de mot Opaque.

> Le mot *Opaque* désigne un type de secret. *Opaque* est le type
> par défaut de secret, sous forme clé-valeur. *Opaque* souligne
> la protection (obfuscation) de la variable environnement

Dans ce fichier, nous allons stocker le mot de passe chiffré au
format Base64.

Pour ce faire, on utilise:

```bash
echo -n mon-mot-de-passe | base64
```

Rajoutez les valeurs chiffrées obtenues du chiffrage dans le
fichier `secret.yaml` dans la place appropriée.

> Voir [secret.yaml](secret.yaml)

Et appliquez-le en utilisant `kubectl apply` et vérifiez sa bonne
création.

```bash
kubectl apply -f secret.yaml
```

### 1.3. Création de la ressource `PersistentVolumeClaim`

Mysql a besoin d’une espace de stockage pour stocker les bases de données.

Pour cela, il faut créer un fichier `mysql-pvc.yaml`

> Voir [mysql-pvc.yaml](mysql-pvc.yaml)

Qu’est-ce qu’il signifie ReadWriteOnce dans ce fichier ?

> `ReadWriteOnce` (__RWO__), permet à un volume de stockage d'être
> monté en __lecture-écriture__ par __un seul nœud__ à la fois,
> idéal pour avoir l'__exclusivité d'écriture__ (!= *concurrency*).

Appliquez le fichier.

```bash
kubectl apply -f mysql-pvc.yaml
```

### 1.4. Création du déploiement

Créez le déploiement en remplissant les valeurs manquantes
et appliquez-le:

> Voir [mysql-deployment.yaml](mysql-deployment.yaml):
> - On utilise le port par défaut pour mysql (3306)
> - On référence nos volumes et secrets appliquées précedemment

```bash
kubectl apply -f mysql-deployment.yaml
```

### 1.5. Création du service

Dans cet exemple, le service MySQL ne sera  pas exposé en dehors
du cluster Kubernetes.

Seuls les autres pods du cluster pourront y accéder.

Créez un nouveau fichier nommé `service.yaml` et appliquez-le:

> Voir [service.yaml](service.yaml)

```bash
kubectl apply -f service.yaml
```

Lancez le pod et accédez à sa ligne de commande.

```bash
$ kubectl get pods

NAME                               READY   STATUS    RESTARTS   AGE
mysql-deployment-8648d76b6-g6c2s   1/1     Running   0          59s
```

```bash
kubectl exec -it pods/mysql-deployment-8648d76b6-g6c2s -- bash
```

Entrez le mot de passe en utilisant la commande suivante:

```bash
mysql -p
```

À la fin de cette partie, supprimez toutes les ressources
créées précédemment.

```bash
kubectl delete deployments mysql-deployment
```

## 2. Déployer une application avec secret non codé

Dans cette section, vous allez répéter les mêmes étapes précédentes
sauf pour la première étape où vous allez créer un secret en clair.

Qu’est-ce qu’il va changer dans le fichier secret ?

Inspirez-vous de la [documentation](https://kubernetes.io/docs/concepts/configuration/secret/).

> Voir [secret2.yaml](secret2.yaml)
>
> On modifie:
> - `data` => `stringData`
> - Mot de passe non encodé en base64

## 3. Déploiement de deux applications : Mysql et Wordpress

À vous de jouer : Considérez les deux déploiements suivants
et interprétez ce qui se passe.

1. Qu’est-ce qu’il manque dans ces deux déploiements?
Faites le nécessaire !!

Il manque dans les 2 déploiements les `Secret`.

On ajoute donc dans les deux déploiements:

```yaml
apiVersion: v1
kind: Secret
metadata: mysql-pass
type: Opaque
stringData:
  password: mypass
```

2. Dessinez un schéma de l’interaction entre les pods dans
ces deux déploiements.

> TODO: Schema (Bonus)

3. Cherchez la signification des mots surlignés dans la documentation Kubernetes:

- `kind: Service`
  - objet API qui expose un ou plusieurs Pods en réseau,
  avec une adresse IP statique
- `tier: mysql` & `tier: frontend`
  - Les `tier` sont des types de labels qui servent d'indications
  pour les devs/users (différencier les services backend/frontend, etc).
- `kind: PersistentVolumeClaim`
  - Demande de stockage pour utiliser un `PersistentVolume`, en
  spécifiant les besoins (volume, etc.).
- `ReadWriteOnce`
  - permet à un volume de stockage d'être monté en lecture-écriture
  par un seul nœud à la fois (!= *concurrency*).
- `kind: Deployment`
  - objet API qui gère le déploiement et la mise à jour des Pods.
- `type: Recreate`
  - Stratégie de déploiement qui termine tous les anciens Pods avant de déployer les nouveaux.
- `type: LoadBalancer`
  - Répartit automatiquement le trafic réseau entre plusieurs Pods.

### 3.1. Déploiement n°1

```yaml
apiVersion: v1
kind: Service
metadata:
  name: wordpress-mysql
  labels:
    app: wordpress
spec:
  ports:
  - port: 3306
  selector:
    app: wordpress
    tier: mysql
  clusterIP: None
---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: mysql-pv-claim
  labels:
    app: wordpress
spec:
  accessModes:
  - ReadWriteOnce
  resources:
    requests:
      storage: 2Gi
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: wordpress-mysql
  labels:
    app: wordpress
spec:
  selector:
    matchLabels:
      app: wordpress
      tier: mysql
  strategy:
    type: Recreate
  template:
    metadata:
      labels:
        app: wordpress
        tier: mysql
    spec:
      containers:
      - image: mysql:5.6
        name: mysql
        env:
        - name: MYSQL_ROOT_PASSWORD
          valueFrom:
            secretKeyRef:
              name: mysql-pass
              key: password
        - name: MYSQL_DATABASE
          value: wordpress
        - name: MYSQL_USER
          value: wordpress
        - name: MYSQL_PASSWORD
          valueFrom:
            secretKeyRef:
              name: mysql-pass
              key: password
        ports:
        - containerPort: 3306
          name: mysql
        volumeMounts:
        - name: mysql-persistent-storage
          mountPath: /var/lib/mysql
      volumes:
      - name: mysql-persistent-storage
        persistentVolumeClaim:
          claimName: mysql-pv-claim
```

### 3.2. Déploiement n°2

```yaml
apiVersion: v1
kind: Service
metadata:
  name: wordpress
  labels:
    app: wordpress
spec:
  ports:
  - port: 80
  selector:
    app: wordpress
    tier: frontend
  type: LoadBalancer
---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: wp-pv-claim
  labels:
    app: wordpress
spec:
  accessModes:
  - ReadWriteOnce
  resources:
    requests:
      storage: 2Gi
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: wordpress
  labels:
    app: wordpress
spec:
  selector:
    matchLabels:
      app: wordpress
      tier: frontend
  strategy:
    type: Recreate
  template:
    metadata:
      labels:
        app: wordpress
        tier: frontend
    spec:
      containers:
      - image: wordpress:latest
        name: wordpress
        env:
        - name: WORDPRESS_DB_HOST
          value: wordpress-mysql
        - name: WORDPRESS_DB_PASSWORD
          valueFrom:
            secretKeyRef:
              name: mysql-pass
              key: password
        - name: WORDPRESS_DB_USER
          value: wordpress
        ports:
        - containerPort: 80
          name: wordpress
        volumeMounts:
          - name: wordpress-persistent-storage
            mountPath: /var/www/html
      volumes:
      - name: wordpress-persistent-storage
        persistentVolumeClaim:
          claimName: wp-pv-claim
```
