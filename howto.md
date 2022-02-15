```sh
~/sw % git clone git@github.com:rinpatch/KotlinAsFirst2020.git
Cloning into 'KotlinAsFirst2020'...
remote: Enumerating objects: 4851, done.
remote: Total 4851 (delta 0), reused 0 (delta 0), pack-reused 4851
Receiving objects: 100% (4851/4851), 962.22 KiB | 1.91 MiB/s, done.
Resolving deltas: 100% (2334/2334), done.
~/sw % cd KotlinAsFirst2020
~/s/KotlinAsFirst2020 (master)% git remote add upstream-my git@github.com:rinpatch/KotlinAsFirst2021.git
~/s/KotlinAsFirst2020 (master)% git fetch upstream-my
remote: Enumerating objects: 628, done.
remote: Counting objects: 100% (73/73), done.
remote: Compressing objects: 100% (5/5), done.
remote: Total 628 (delta 67), reused 70 (delta 67), pack-reused 555
Receiving objects: 100% (628/628), 190.44 KiB | 924.00 KiB/s, done.
Resolving deltas: 100% (291/291), completed with 19 local objects.
From github.com:rinpatch/KotlinAsFirst2021
 * [new branch]      master     -> upstream-my/master
~/s/KotlinAsFirst2020 (master)% git log upstream-my/master
[...]
~/s/KotlinAsFirst2020 (master)% # rebasing from 1137b420cc95fa6894edad69b31e2da1bb985d1d
~/s/KotlinAsFirst2020 (master)% git rebase --onto master 1137b420cc95fa6894edad69b31e2da1bb985d1d upstream-my/master
Auto-merging src/lesson11/task1/Complex.kt
CONFLICT (content): Merge conflict in src/lesson11/task1/Complex.kt
error: could not apply 2dc7367... lesson11: Solve Complex
hint: Resolve all conflicts manually, mark them as resolved with
hint: "git add/rm <conflicted_files>", then run "git rebase --continue".
hint: You can instead skip this commit: run "git rebase --skip".
hint: To abort and get back to the state before "git rebase", run "git rebase --abort".
Could not apply 2dc7367... lesson11: Solve Complex
~/s/KotlinAsFirst2020  (git)-[2dc73676fd02b4b040fb8b2951476d5dd6e8add1|rebase-i]-% # 2020 lesson11 wants a constructor from string instead of a function. Let's fix that.
~/s/KotlinAsFirst2020  (git)-[2dc73676fd02b4b040fb8b2951476d5dd6e8add1|rebase-i]-% vi src/lesson11/task1/Complex.kt
~/s/KotlinAsFirst2020  (git)-[2dc73676fd02b4b040fb8b2951476d5dd6e8add1|rebase-i]-% git add src/lesson11/task1/Complex.kt
~/s/KotlinAsFirst2020  (git)-[2dc73676fd02b4b040fb8b2951476d5dd6e8add1|rebase-i]-% git rebase --continue
[detached HEAD 1bb909a] lesson11: Solve Complex
 1 file changed, 42 insertions(+), 10 deletions(-)
Successfully rebased and updated detached HEAD.
~/s/KotlinAsFirst2020 (1bb909a)% git checkout -b backport
Switched to a new branch 'backport'
~/s/KotlinAsFirst2020 (backport)% git checkout master
~/s/KotlinAsFirst2020 (master)% git merge backport
Updating d535f35..1bb909a
Fast-forward
 src/lesson1/task1/Simple.kt       |  39 ++++++++++----
 src/lesson11/task1/Complex.kt     |  52 ++++++++++++++----
 src/lesson12/task1/OpenHashSet.kt |  58 ++++++++++++++++++--
 src/lesson2/task1/IfElse.kt       |  98 +++++++++++++++++++++++++++++++---
 src/lesson2/task2/Logical.kt      |  33 ++++++++++--
 src/lesson3/task1/Loop.kt         | 169 +++++++++++++++++++++++++++++++++++++++++++++++++++++-----
 src/lesson4/task1/List.kt         | 142 ++++++++++++++++++++++++++++++++++++++++++++-----
 src/lesson5/task1/Map.kt          | 104 +++++++++++++++++++++++++++++++-----
 src/lesson6/task1/Parse.kt        | 171 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++---
 src/lesson7/task1/Files.kt        | 219 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++---
 src/lesson9/task1/Matrix.kt       |  50 ++++++++++++++----
 src/lesson9/task2/Matrices.kt     | 191 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--
 test/lesson2/task1/Tests.kt       |   1 +
 test/lesson3/task1/Tests.kt       |   3 ++
 test/lesson4/task1/Tests.kt       |   5 ++
 test/lesson5/task1/Tests.kt       |  11 ++++
 test/lesson6/task1/Tests.kt       |   7 +++
 test/lesson7/task1/Tests.kt       |  40 ++++++++++++++
 test/lesson9/task1/Tests.kt       |   1 +
 test/lesson9/task2/Tests.kt       |  20 +++++++
 20 files changed, 1306 insertions(+), 108 deletions(-)
~/s/KotlinAsFirst2020 (master)% git merge -s ours upstream-theirs/master
Merge made by the 'ours' strategy.
~/s/KotlinAsFirst2020 (master)% git remote -v > remotes
~/s/KotlinAsFirst2020 (master)% git add remotes
~/s/KotlinAsFirst2020 (master)% git commit -m "Add remotes"
[master fb224af] Add remotes
 1 file changed, 6 insertions(+)
 create mode 100644 remotes
~/s/KotlinAsFirst2020 (master)% vi howto.md
