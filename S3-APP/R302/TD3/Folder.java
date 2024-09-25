// ASH MERIENNE & EMMA ESCOFFIER

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
    public static void rmdir (Tree<String> hierarchy, String name){ // anciennement update()

        if (hierarchy.data().equals(name)) {
            Tree<String> parent = hierarchy.parent();  // Get the parent node

            if (parent != null) {
                // Remove node from parent child list
                // Had to do this because it's the only way to get a reference
                parent.children().remove(hierarchy);
                System.out.println("Removed node: " + name);
            } else {
                System.out.println("Cannot remove the root node: " + name);
            }
            return;
        } else if (!hierarchy.children().isEmpty()) {
            for (int i=0; i<hierarchy.children().size(); i++)
            {
                rmdir(hierarchy.children().get(i), name);
            }
        } else return;
    }

    /* Créer les répertoires à partir de l'arbre */
    public static void createFolders(File dir, Tree<String> hierarchy) {

        dir.mkdirs();

        ArrayList<Tree<String>> childNodes = hierarchy.children();

        for (Tree<String> childNode : childNodes)
        {
            File nodeFile = new File(childNode.data());

            if (childNode.nbChildren() > 0) {
                // make sure node is a folder and not a file
                // doesnt work with empty folders
                String path = dir.getAbsolutePath() + "/" + nodeFile.getName();
                File newFolder = new File(path);
                createFolders(newFolder, childNode);
            }
        }

    }


    public static void main(String... args){
        File currentDir = new File("./rootLevel");

        Tree<String> hierarchy = buildTree(currentDir);
        hierarchy.display();

        rmdir(hierarchy, "secondLevel");
        hierarchy.display();

        currentDir = new File("newDirectory");
        createFolders(currentDir, hierarchy);
    }
}
