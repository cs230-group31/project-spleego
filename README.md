# Project Spleego

Group 31 Assignment 2.

## Logging

Please use the built in logger instead of using `System.out.println("something");`

To use the inbuilt logger, simply import it:

```java
import com.group31.logger.Logger;
```

Syntax of the logger class:

```java
Logger.log("Message To Log", Logger.Level.INFO)
```

The levels are:


```java
Logger.Level.INFO
Logger.Level.WARNING
Logger.Level.ERROR
Logger.Level.VERBOSE
```

## Linting

Please install SonarLint for IntelliJ. It can be found by going to `Plugins > Marketplace > search for SonarLint`.

All code must pass the linter's rules before it can be committed.

## Committing Rules

Please do not commit straight into the `master` branch! Rather create a branch and push your code. This allows for other
group members to review the code before it is added to the `master` branch.

Creating a branch is simple, if you are using command-line:

```sh
git checkout -b <branch_name>
```

For example:

```sh
git checkout -b liam
```

If you are using the Github desktop app, click the `Current branch` button and click `New`. Follow the on-screen prompts.

![Github Desktop New Branch](https://i.imgur.com/bE5K5Ui.png)

### Pull Requests

When you are happy with your branch, you are able to create a merge request. All members of the group are required to approve
or ask for changes on each merge request.

Only merge your code when all of the group members have approved the request. Please use `Squash and Merge`.

As a reviewer, you **should not** merge another members code. It is up to the member who created the request to merge the
code, you just need to approve it or ask for changes.

### Reviewing

Please review all commits made by other members in the group. Should you disagree with how something has been programmed,
please leave a comment on the merge request.

### Merging

Once all group members have approved your code, you will then be allowed to merge it with the master branch. All code
that is in the master branch should run **without any** errors or warnings!

### After Pull Requests

Please ensure to delete the branch from both Github and your local system before starting new work.

**Do not** delete other people's branches. To avoid confusion it is suggested that you name your branch after yourself.
