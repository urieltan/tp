---
layout: page
title: Lin Yuan Xun, Caleb's Project Portfolio Page
---

## Project: AddressBook Level 3

Lifebook is a desktop task management application created as an assignment for a module that teaches Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New feature: TaskList and tasks**: Added the ability to create `Tasks`, and add or delete them to or from the `TaskList` respectively.
  * What it does: allows the user create to tasks that may either be `Todos` or `Events` and keep a record of them in the `TaskList`.
  * Justification: This feature improves the product significantly because a user can now record information about tasks in addition to addresses in Lifebook for future reference and planning.
  * Highlights: This required an implementation of the TaskList and Tasks (along with its subclasses). This also provides an opportunity for more commands and features to be added in the future.
  * Credits: inspired from the creation of Bob - the personal assistant, which was my individual project.

* **New feature List out Tasks, Events or Todos**: Implemented the ability for users to list out all `Tasks`, `Events`, or `Todos`.
  * What it does: allows the user to list out `Tasks`, `Events`, or `Todos`.
  * Justification: allows the user to view all `Tasks`, or if needed, a filtered list of all `Events` or `Todos`.
  * Credits: inspired from the creation of Bob - the personal assistant, which was my individual project.

* **New feature: Mark tasks as done**: Implemented the ability for users to mark tasks as done.
  * What it does: allows the user to mark completed tasks as done.
  * Justification: allows users to have a record of the tasks that have been completed.
  * Credits: inspired from the creation of Bob - the personal assistant, which was my individual project.

* **New feature: Sort TaskList and AddressBook** Implemented the ability for users to sort the `TaskList` and `AddressBook`.
  * What it does: allows users to sort the `TaskList` and `AddressBook` by name and date respectively. Users also have the option of restoring both lists to their natural order if needed.
  * Justification: provides users with intuitively sorted lists. Users normally prefer to look through contact details in alphabetical order, and task details in the order of imminence.
  * Highlights: this required a change in the model component. To implement sorting easily, the `TaskList` and `AddressBook` had to be wrapped by the JavaFX sorted list class, since the JavaFX filtered list class does not support sorting.

* **New feature: TaskList GUI**: Created a simple GUI for the `TaskList` and `Tasks` in the first iteration, which was further enhanced and modified in proceeding iterations by other members.
  * What it does: allows users to view the `TaskList` and its contents.
  * Justification: provides a means for users to easily access the contents of the `TaskList` via a graphical representation.
  * Highlights: made it easier for the developing team to visualise any features that they may have implemented in regards to the `TaskList` and/or `Tasks`. Also, provides a starting point for further development of its GUI. Implementing this GUI was initially challenging due to my lack of my experience. It required a big change in my initial implementation of the TaskList so that it would be observable to the GUI.
  * Credits: the initial GUI implementation for the `TaskList` was inspired by the GUI of the AddressBook.

* **New feature: Storage for TaskList**: Implemented storage for the `TaskList`
  * What it does: allows users to store `TaskList` data.
  * Justification: provides a means for users to easily access the contents of the `TaskList` in future sessions.
  * Highlights: enables greater ease of implementing storage for data of other features pertaining to `Tasks` in the future. I had encountered some challenges due to my lack of familiarity with JSON, but implementing Storage with it had provided me with some valuable experience.
  * Credits: implementation inspired by the existing `Storage` component of address book.



* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=F12-4&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=functional-code~docs~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=caleblyx&tabRepo=AY2021S1-CS2103T-F12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~docs~test-code)

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Reduced coupling of `Model` and `Logic` in the implementation of the Recurrence feature. Initially, `Model` had a dependency on `Logic` in the implementation of Recurrence, since `Model` had to return a `Command`, which is a component of `Logic`. This implementation was enhanced, removing this dependency, such that only `Logic` would have a dependency on `Model`. [\#148](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/148)
  * Wrote unit tests for all added storage components to support storage of the contents of `TaskList`. [\#134](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/134)

* **Documentation**:
  * User Guide:
    * Added documentation for the features:
        * `add todo`, `remove todo`, `mark todo as done` and `list todo`. [#\20](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/20)
        * `sort contact`, `sort task`, and `sort clear` [\#110](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/110) [\#145](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/145);
  * Developer Guide:
    * Added implementation details of the `Add Task` feature. [#\120](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/120)

* **Community**:
  * PRs reviewed and merged: [\#149](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/149), [\#216](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/216), [\#221](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/221).
  * Resolve failing CI with PR: [\#150](https://github.com/AY2021S1-CS2103T-F12-4/tp/pull/150)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/Caleblyx/ped/issues/6), [2](https://github.com/Caleblyx/ped/issues/4), [3](https://github.com/Caleblyx/ped/issues/3))


