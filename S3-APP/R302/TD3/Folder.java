import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Folder {
    /* Construire l'arbre des dossiers à partir de l'adresse indiquée */
    public static Tree<String> buildTree(File dir) {

        if (!dir.exists()) {
            System.out.println("Directory does not exist : " + dir);
            return null;
        } else if (!dir.isDirectory()) {
            System.err.println("Is a fine, not directory : " + dir);
            return null;
        }

        File[] fileTab = dir.listFiles();

        Tree<String> hierarchy = new Tree<String>(dir.getName());

        assert fileTab != null;
        for (File file : fileTab)
        {
            if (file.isDirectory()) {
                hierarchy.addChildren(buildTree(file));
            } else {
                Tree<String> fileNode = new Tree<>(file.getName());
                hierarchy.addChildren(fileNode);
            }
        }


        return hierarchy;
    }

    /* Un algorithme qui modifie l'arbre parmi ceux proposés.
    * Vous pouvez changer le nom en fonction */
    public static void update (Tree<String> hierarchy, String name){

        

        return;
    }

    /* Créer les répertoires à partir de l'arbre */
    public static void createFolders(File dir, Tree<String> hierarchy) {

        dir.mkdirs();

        //hierarchy.display();
        ArrayList<Tree<String>> childNodes = hierarchy.children();

        for (Tree<String> childNode : childNodes)
        {
            File nodeFile = new File(childNode.data());

            if (childNode.nbChildren() > 0) {
                String path = dir.getAbsolutePath() + "/" + nodeFile.getName();
                System.out.println("Folder: " + nodeFile.getName());
                System.out.println("Path: " + path + "\n");
                File newFolder = new File(path);
                createFolders(newFolder, childNode);
            }
        }

    }


    public static void main(String... args){
        File currentDir = new File("./");

        Tree<String> hierarchy = buildTree(currentDir);
        //hierarchy.display();

        //update(hierarchy, "name");
        //hierarchy.display();

        currentDir = new File("newDirectory");
        createFolders(currentDir, hierarchy);
    }
}