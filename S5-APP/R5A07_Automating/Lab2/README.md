# Lab 2

Git: fork, clone, branches. Shell scripting: parameters.

## 1. Git fork and clone

The objective of this exercise is to set up a Git repository for your lab files,
based on an existing repository prepared for you by the teachers.

### a. Examine the teachers’ repository.

https://git.iut-orsay.fr/apaskev/s5-automation-students.

How many branches does it have? What is the date and the author of the last commit? How many open issues does it contain? Who is its owner?

> It has 1 branch: 'main'
>
> Last commit: Andrei Paskevich - 2025-09-15 11:33:50 GMT+2
>
> Open issues: 6
>
> Owner: Andriy Paskevich

### b. Fork it to create you own instance.

To this aim, in the web interface of the teacher’s repo, click
on `Fork`. Select the name `s5-automation`, your own name space and private visibility.

### c. Gitlab will bring you to the forked version. Pin the most useful menu items

Such as `Plan → Wiki`, `Code → Repository`, and `Code → Branches`.

Check that your forked version contains the same
branches and files as the original teacher’s repository.

### d. In the teachers’ repository, got to `Issues → Actions`.

Export the issues as a `.csv` file. They will be sent to you per email.

If you do not receive the email, use the file that is located in `Lab2` of your fork.

Import them to your forked repo, check that they are correctly imported.

### e. Who is now the owner of your forked version?

Answer this question in the issues.

### f. Add your lab teacher with the Maintainer role.

### g. If needed, add an SSH key.

As explained in the S4 cours (S4 Qualite de developpement)

### h. Clone the repository in your local folder.

### i. What is the difference between a clone and a fork?

Answer this question in the issues.

## 2. Git branches

### a. Examine the contents of your forked repository.

It should contain the file [`makeTree.sh`](../Lab1/makeTree.sh) in the
[`Lab1` directory](../Lab1/).

It is a solution to exercise 2 from Lab 1.

### b. Create a branch called `lab-2-parameters` and switch to it.

Check the number of the existing branches and the current branch.

Check that the new branch still contains the Lab-1 directory
and the makeTree.sh file.

### c. Go to Lab-1 and run the makeTree.sh script

Check that it created a correct `corpus/` directory as in Lab 1.

### d. Remove the whole corpus directory with one command and re-create it with the same script.

```bash
rm -rf corpus
source makeTree.sh
```

## 3. Script parameters

### a. Make sure that you are still in the `lab-2-parameters` branch.

Enter the directory `Lab-2`.

Copy the `Lab-1/makeTree.sh` to `Lab-2/makeTreeParam.sh`.

Add `Lab-2/makeTreeParam.sh` to Git.

### b. Transform `makeTreeParam.sh`.

In the following way:
- it should now take one parameter: the name of a language (e.g. French, Romanian, Georgian,
Haitian, etc.),
- it should create a `corpus/` directory as before, but the name of the language is given by the command line parameter,
- the `corpus/` and the `corpus/bin/` directories are created only if they do not exist yet (`mkdir -p`),
- the input parameter is documented in the header comment,
- all other useful comments are present, as explained in Lab 1,
- the sample run below is imitated.

Do not forget to commit and push the file regularly.

### c. Once you are sure that your `makeTreeParam.sh` script is working correctly

Switch to the main branch, merge the `lab-2-parameters` branch into it (not the opposite!)

Push your modifications.

Check that the main branch contains `Lab-2` and `Lab-2/makeTreeParam.sh`.

Delete the `lab-2-parameters` branch.

> This methodology will be used in the following labs.
> 
> For each exercise, you will create a new branch,
> develop the solution and test it.
> 
> Once you are sure it is working, you will merge the
> exercise branch into main and delete the exercise branch.
> 
> Don’t forget to commit and push often.
