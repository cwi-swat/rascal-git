A library for working with Git repositories from inside Rascal.

This is currently under development. Feel free to add an issue if you are looking for a different feature. So far, the following functionality should work.

### Cloning a Remote Repository

To check out a remote repository, use the function `cloneRemoteRepository`. This function takes two parameters: the path to the remote repository (as a `str`), and the location where the repository will be stored (as a `loc`). For instance, to check the current version of [WordPress](https://wordpress.org/) out from the GitHub repository at [https://github.com/WordPress/WordPress](https://github.com/WordPress/WordPress), you would use the command:

```
cloneRemoteRepository("https://github.com/WordPress/WordPress.git", |file:///tmp/wp|);
```

This would check out the repository and store it in the directory `/tmp/wp` on your local drive. Note that this directory is the root of the repo, the repo is not in another directory inside it (i.e., the `.git` directory will be in `/tmp/wp`, not inside `/tmp/wp/WordPress`).

### Opening a Local Repository

To open a Git repository that is stored locally, including one checked out using `cloneRemoteRepository`, you use the `openLocalRepository` command. For instance, to open the repository cloned above, you would use the command:

```
openLocalRepository(|file:///tmp/wp|);
```

This creates an internal object representing the repository. This needs to be done first, before any of the other commands are used. Note that `cloneRemoteRepository` also does this, but would only be used when you first clone the repository.

### Getting Tags

To get all the tags from a Git repository, use the `getTags` command. For instance, the following command gets all tags from the WordPress repository cloned above:

```
tags = getTags(|file:///tmp/wp|);
```

### Switching To a Specific Tag

To check out a specific tag in an existing repository, use the `switchToTag` command. For instance, to switch to version 4.6.8 of WordPress, use the command:

```
switchToTag(|file:///tmp/wp|, "4.6.8");
```

Note that this actually switches to this tag in the repo itself, so if you go to the repo and run the `git log` command, you will see that the current commit is tagged as release 4.6.8.
