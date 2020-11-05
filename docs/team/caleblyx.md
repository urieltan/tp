---
layout: page
title: John Doe's Project Portfolio Page
---

## Project: AddressBook Level 3

Lifebook is a desktop task management application created as an assignment for a module that teaches Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **TaskList and tasks**: Implemented `TaskList` and `Tasks`. Added the ability to create `Tasks`, and add or delete them to or from the `TaskList` respectively.
  * What it does: allows the user create to tasks that may either be `Todos` or `Events` and keep a record of them in the `TaskList`.
  * Justification: This feature improves the product significantly because a user can now record information about tasks in addition to addresses in Lifebook for future reference and planning.
  * Highlights: provides an opportunity for more commands and features to be added in the future.
  * Credits: inspired from the creation of Bob - the personal assistant, which was my individual project.
  **List out Tasks, Events or Todos**: Implemented the ability for users to list out all `Tasks`, `Events`, or `Todos`.
  * What it does: allows the user to list out `Tasks`, `Events`, or `Todos`.
  * Justification: allows the user to view all `Tasks`, or if needed, a filtered of all `Events` or `Todos`.
  * Credits: inspired from the creation of Bob - the personal assistant, which was my individual project.
  * **Mark tasks as done**: Implemented the ability for users to mark tasks as done.
  * What it does: allows the user to mark completed tasks as done.
  * Justification: allows users to have a record of the tasks that have been completed.
  * Credits: inspired from the creation of Bob - the personal assistant, which was my individual project. 
* **Sort TaskList and AddressBook** Implemented the ability for users to sort the `TaskList` and `AddressBook`.
  * What it does: allows users to sort the `TaskList` and `AddressBook` by name and date respectively. Users also have the option of removing all sorting and restoring both lists to their natural order if needed.
  * Justification: provides users with intuitively sorted lists. Users normally prefer to look through contact details in alphabetical order, and task details in the order of imminence.
  * Highlights: this required a change in the model component. To implement sorting easily, the `TaskList` and `AddressBook` had to be wrapped by the JavaFX sorted list class, since the JavaFX filtered list class does not support sorting.
* **Basic TaskList GUI**: Created a simple GUI for the `TaskList` and `Tasks` in the first iteration, which was further enhanced and modified in proceeding iterations by other members.
  * What it does: allows users to view the `TaskList` and its contents.
  * Justification: provides a means for users to easily access the contents of the `TaskList` via a graphical representation. 
  * Highlights: made it easier for the developing team to visualise any features that they may have implemented with regards to the `TaskList` and/or `Tasks`. Also, provides a starting point for further development of its GUI. Implementing this GUI was initially challenging due to my lack of my experience. It required a big change in my initial implementation of the TaskList so that it would be observable to the GUI.
  * Credits: the initial GUI implementation for the `TaskList` was inspired by the GUI of the AddressBook.
* **Storage for TaskList**: Implemented storage for the `TaskList`
  * What it does: allows users to store `TaskList` data.
  * Justification: provides a means for users to easily access the contents of the `TaskList` in future sessions.
  * Highlights: enables greater ease of implementing storage for data of other features pertaining to `Tasks` in the future.
  * Credits: implementation inspired by the existing `Storage` component of address book.
  


* **Code contributed**: [RepoSense link]()

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
