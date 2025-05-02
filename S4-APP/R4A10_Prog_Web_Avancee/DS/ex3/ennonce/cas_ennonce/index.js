let contenu_du_main = {
   tag       : 'main',
   attributs : [ ['class','joli'] ],
   fils      : [
      {
         tag       : 'h1',
         attributs : [],
         fils      : [
            { texte : 'titre un' }
         ]
      },      
      {
         tag       : 'section',
         attributs : [],
         fils      : [ 
            {
               tag       : 'article',
               attributs : [],
               fils      : [ 
                  {
                     tag       : 'h2',
                     attributs : [],
                     fils      : [ 
                        { texte : 'titre deux' }
                     ]
                  },
                  {
                     tag       : 'p',
                     attributs : [],
                     fils      : [
                        { texte : 'paragraphe un' }
                     ]
                  },
                  {
                     tag       : 'p',
                     attributs : [],
                     fils      : [
                        { texte : 'paragraphe deux' }
                     ]
                  },
               ]
            },
            {
               tag       : 'article',
               attributs : [ ['class','attention'] ],
               fils      : [ 
                  {
                     tag       : 'p',
                     attributs : [],
                     fils      : [
                        { texte : 'paragraphe trois' }
                     ]
                  },
               ]
            },
         ]
      },      
   ]
};


function handle_attributs(node, attributs) {
   for (let attr of attributs) {
      let type = attr[0];
      let val = attr[1];
      if (type == 'class') node.classList.add(val);
      else console.info(`Attribut de type ${type} : ${val}`);
   }
}


function handle_fils(parent, fils) {
   for (let elem of fils) {
      if (elem.texte) {
         parent.innerText = elem.texte;
         continue;
      }

      let node = document.createElement(elem.tag);
      handle_attributs(node, elem.attributs);
      parent.appendChild(node);
      if (elem.fils) handle_fils(node, elem.fils);
   }
}

function main() {
   let main = document.querySelector('main');
   console.log(contenu_du_main)

   handle_attributs(main, contenu_du_main.attributs);
   handle_fils(main, contenu_du_main.fils);
}


document.addEventListener(
	"DOMContentLoaded",
	(event) => main()
);
