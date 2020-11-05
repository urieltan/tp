---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

**How the architecture components interact with each other**

### UI component

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2103T-F12-4/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

### Logic component

**API** :
[`Logic.java`](https://github.com/AY2021S1-CS2103T-F12-4/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

### Model component

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103T-F12-4/tp/blob/master/src/main/java/seedu/address/model/Model.java)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2021S1-CS2103T-F12-4/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

### Common classes

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add tasks (`todo` and `event`) feature

##### Parser:

![AddTaskParserClassDiagram](images/addTask/AddTaskParserClassDiagram.png)

* `AddCommandParser` implements `Parser<AddCommand>`

* It parses the user input to determine if the user intends to add a `todo`, `event`, or `person`.
* It parses the input after the prefixes required to create the intended `todo`, `event`, or `person`.
* If the user input has all all required prefixes and matches the required syntax and format, it creates the new intended Task or `person` and passes it to its respective AddCommand constructor.

##### Command:

![AddTaskCommandClassDiagram](images/addTask/CommandClassDiagram.png)

* The abstract class `AddCommand` extends `Command`.
* The concrete classes `AddTodoCommand` and `AddEventCommand` extends `AddCommand`.
* The command will be executed by the Model, which will update the FilteredTaskList based on the added task.
* If it is successful, it will return a CommandResult with a successful message to the UI.

---
The following sequence diagrams displays a `Todo` being added to the Task List. Adding an `Event` follows a similar sequence.

![AddSequenceDiagram](images/addTask/AddSequenceDiagram.png)

The following sequence diagram exhibits the behavior of logic.

![AddTaskSequenceDiagram](images/addTask/AddTaskSequenceDiagram.png)

The following activity diagram shows what happens when the user enters an add task command:

![AddTaskActivityDiagram](images/addTask/AddTaskActivityDiagram.png)

#### Design consideration

#### How command works:

* An alternative approach would be to have a single `AddTaskCommand` which extends `AddCommand`. The `AddCommandParser` could pass either `todo` or `event` to this class' constructor.
* This could reduce the replication of code, since both `AddTodoCommand` and `AddEventCommand` are almost identical.
* However, by having two distinct commands, different and more specific success or error messages can be produced by the execution of respective commands.

### Filter tasks (`dueAt` and `dueBefore`) feature

##### Parser:

![ParserClassDiagram](images/filterFunction/ParserClassDiagram.png)

* `DueBeforeCommandParser` implements `Parser<DueBeforeCommand>`

    * It checks for the phrase `itemsDueBefore` and parses the input after the prefixes: date `date/` and time `time/`.
    * If the input are in the correct date and time format, a new DueBeforePredicate object is created and passed
    to a new DueBeforeCommand constructor.

* `DueAtCommandParser` implements `Parser<DueAtCommand>`

    * It checks for the phrase `itemsDueAt` and parses the content after the prefixes: date `date/` and time `time/`.
    * If the input are in the correct date and time format, a new DueAtPredicate object is created and passed to a new DueAtCommand constructor.

##### Predicate:

![PredicateClassDiagram](images/filterFunction/PredicateClassDiagram.png)

The way dueAt and dueBefore works is very similar, the difference only being the dueBefore and dueAt predicate.

`DueBeforePredicate` and `DueAtPredicate` extends `DuePredicate`.


* `DueBeforePredicate` compares the LocalDateTime input and every task's LocalDateTime, and returns true if the task's LocalDateTime *is before* the input's LocalDateTime.
* `DueAtPredicate` compares the LocalDateTime input and every task's LocalDateTime, and returns true if the task's LocalDateTime *equals* the input's LocalDateTime.

##### Command:
The class diagram

![CommandClassDiagram](images/filterFunction/CommandClassDiagram.png)

* `DueBeforeCommand` and `DueAtCommand` extends `Command`.
* The command will be executed with the `Model`, which will update the `FilteredTaskList` based on the `DueAtPredicate`/`DueBeforePredicate`
* If it is successful, it will return a `CommandResult` with a successful message to the UI.

The following sequence diagram shows how the dueAt filtering works:

![FilterSequenceDiagram](images/filterFunction/FilterSequenceDiagram.png)

The following activity diagram shows what happens when the user enters the filter command:

![FilterActivityDiagram](images/filterFunction/FilterActivityDiagram.png)

#### Design consideration:

##### Aspect: How dueAt and dueBefore executes

After implementing the task operations, there is `FilteredTaskList` which we can utilise to filter tasks.

By using the same function, we can prevent duplication of code.

Furthermore, we have adhered a similar design to the task's operations (Using of Command, Parser classes) to maintain code consistency.

### Add link to tasks (`link meeting` and `link doc`) feature

##### Parser:

![ParserClassDiagram](images/linkFunction/ParserClassDiagram.png)

* `LinkCommandParser` implements `Parser<LinkCommand>`

    * It checks for the phrase `link meeting` for LinkMeetingCommand and parses the input
    after the prefixes: desc `desc/`, url `url/`, index `i/`, date `date/DD-MM-YYYY`, and time `time/HHmm`.
    * It checks for the phrase `link doc` for LinkCollaborativeCommand and parses the input
    after the prefixes: desc `desc/`, url `url/`, and index `i/`.
    * If the inputs are all in the correct format, a new Link object is created and added to an existing task.

##### Command:
 The class diagram

![CommandClassDiagram](images/linkFunction/CommandClassDiagram.png)

-----
The following sequence diagram shows how the LinkCommand works:
* `LinkCollaborativeCommand` and `LinkMeetingCommand` extends `Command`.
* The command will be parsed by `AddressBookParser` and further parsed by `LinkCommandParser`.
* The `LinkCommandParser` will determine whether the command is a `LinkMeetingCommand` or a `LinkCollaborativeCommand`.
* After returning the suitable Link Command, the command will be executed, calling the `setTask()` method of `Model`,
which will update the `TaskList`.
* After updating the task, the `LogicManager` will call `saveLifeBook()` method of `Storage` class to store the update.
* If all are successful, `LinkCommand` will return a `CommandResult` with a successful message to the UI.

![FilterSequenceDiagram](images/linkFunction/LinkSequenceDiagram.png)

The following activity diagram shows what happens when the user enters the link command:

![FilterActivityDiagram](images/linkFunction/LinkActivityDiagram.png)

### Find (`find contact`, `find todo`, and `find event`) feature

#### Parser:

![ParserClassDiagram](images/findFunction/FindCommandParserClassDiagram.png)

* `FindCommandParser` implements `Parser<FindCommand>`

    * It checks for the phrase `find contact` for FindContactCommand and parses the input
    after the prefixes: `n/` and `t/`.
    * It checks for the phrase `find event` for FindEventCommand and parses the input
    after the prefixes: `desc/` and `t/`.
    * It checks for the phrase `find todo` for FindTodoCommand and parses the input
    after the prefixes: `desc/` and `t/`.
    * If the input is correct, a new Predicate object is created and passed to a new FindCommand constructor.

##### Predicate:

![PredicateClassDiagram](images/findFunction/ContactMatchesFindKeywordPredicate.png)
![PredicateClassDiagram](images/findFunction/TaskMatchesFindKeywordPredicate.png)

The way these predicate works is very similar, where the `ContactMatchesFindKeywordPredicate` handles the Person object
and the `TaskMatchesFindKeywordPredicate` handles the Task object.

`ContactMatchesFindKeywordPredicate` implements `Predicate<Person>`.
`TaskMatchesFindKeywordPredicate` implements `Predicate<Task>`.

* `ContactMatchesFindKeywordPredicate` returns true if the person's name contains one of the name keyword given AND one of the tag matches the given tag keyword.
* `TaskMatchesFindKeywordPredicate` returns true if the task's(event or todo) description contains one of the description keyword given AND one of the tag matches the given tag keyword.
* When only name or description prefix and keyword are given, the predicates return true if the person's name or task's description contain one of the keyword given.
* When only tag prefix and keyword are given, the predicates return true if one of the person's or task's tag(s) matches the keyword given.

##### Command:
 The class diagram

![CommandClassDiagram](images/findFunction/FindCommandClassDiagram.png)

-----
The sequence diagram:
* `FindContactCommand`, `FindEventCommand` and `FindTodoCommand` extends `FindCommand`.
* The command will be parsed by `AddressBookParser` and further parsed by `FindCommandParser`.
* The `FindCommandParser` will determine whether the command is a `FindContactCommand`, `FindEventCommand` or a `FindTodoCommand`.
* After returning the suitable FindCommand, the command will be executed,
calling the `updateFiltertedPersonList()` method of `Model` and update the `AddressBook` if it is a `FindContactCommand`, or
the `updateFiltertedTaskList()` method of `Model` and update the `TaskList` if it is a `FindEventCommand` or `FindTodoCommand`.
* After updating the model, the `LogicManager` will call the storage to save the file.
* If all are successful, `FindCommand` will return a `CommandResult` with a successful message to the UI.

The following sequence diagram shows how the `FindContactCommand` works.
The sequence diagrams for `FindEventCommand` and `FindTodoCommand` are very similar to the diagram below
with minor differences in the type of FindCommand returned and function called to update the model.

![FilterSequenceDiagram](images/findFunction/FindCommandSequenceDiagram.png)

![SaveFileDiagram](images/findFunction/SaveLifebook.png)

The following activity diagram shows what happens when the user enters the find contact command:

![FilterActivityDiagram](images/findFunction/FindCommandActivityDiagram.png)

The activity diagram when user enters the find event or find todo command is similar to the diagram above.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
* Students can keep track of tasks from all modules they take efficiently.
* Manage contacts faster than a typical mouse/GUI driven app.
* Increase school productivity.
* An all in one app that makes student's life easier.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new person               |                                                                        |
| `* * *`  | forgetful student                          | add todos and events        | remember to complete important tasks for projects and  attend important events|
| `* * *`  | user                                       | delete a person                | remove entries that I no longer need                                   |
| `* * *`  | forgetful student                          | remove todos and events        | remove tasks that I no longer need |
| `* * *`  | user                                       | find a person by name          | locate details of persons without having to go through the entire list |
| `* * *`  | user                                       | find todos by description          | locate details of todos without having to go through the entire list |
| `* * *`  | user                                       | find events by description          | locate details of events without having to go through the entire list |
| `* * *`  | student                                    | mark todos and events as done  | remember the tasks or assignments that I have completed          |
| ` * * ` | forgetful student                           | search for contacts under a particular tag  | find people I am working with easily
| ` * * ` | forgetful student                           | search for todos and events under a particular tag  | find the task that I am working on
| `* *`    | disorganised student                       | add and remove collaborative links (Google Drive, and many more) to a todo   | find the collaborative link for the project easily |
| `* *`      | disorganised student                       | add, remove, and view zoom links for meetings to an event         | remember my Zoom Links                                      |
| `* *`    | forgetful/disorganised student | search what tasks/meetings are due soon or by a specific date/time (filter) | remember to finish before the deadline|
| `*`      | user with many contacts in the Lifebook | sort persons by name           | locate a person easily                                                 |
| `*`      | student with weekly lectures and tutorials | add recurring tasks         | save time by not adding the same task every week, which is time-consuming|

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Lifebook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Delete a person**

**MSS**

1.  User requests to list persons
2.  Lifebook shows a list of persons
3.  User requests to delete a specific person in the list
4.  Lifebook deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Lifebook shows an error message.

      Use case resumes at step 2.

**Use case: Show contacts with a specific tag**

**MSS**

1.  User requests to list persons
2.  Lifebook shows a list of persons
3.  User requests to show all persons with a specific tag in the list
4.  Lifebook shows all the persons whose tag matching the tag searched

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given tag is empty or invalid.

    * 3a1. Lifebook shows an error message.

      Use case resumes at step 2.

**Use case: Show Todos with a specific tag**

**MSS**

1.  User requests to list todos
2.  Lifebook shows a list of todos
3.  User requests to show all todos with a specific tag in the list
4.  Lifebook shows all the todos whose tag matching the tag searched

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given tag is empty or invalid.

    * 3a1. Lifebook shows an error message.

      Use case resumes at step 2.

**Use case: Show Events with a specific tag**

**MSS**

1.  User requests to list events
2.  Lifebook shows a list of events
3.  User requests to show all events with a specific tag in the list
4.  Lifebook shows all the events whose tag matching the tag searched

  Use case ends.

**Extensions**

* 2a. The list is empty.

Use case ends.

* 3a. The given tag is empty or invalid.

  * 3a1. Lifebook shows an error message.

    Use case resumes at step 2.

**Use case: Add a To Do to the To Do List**

**MSS**
1. User requests to add a To Do and its details (i.e. description, date, and time) to the To Do list.
2. Lifebook acknowledges the request by adding the To Do to the To Do list.

    Use case ends.

**Extensions**

* 1a. User inputs the date or time of the To Do in the incorrect format.

    * 1a1. Lifebook shows an error message

    Use case restarts at step 1.

* 1b. User chooses to input the task as a recurring one

    * 1b1. Lifebook will add the task as a recurring one instead.

**Use case: Perform an action (remove, show, mark as done) on a To Do from the To Do list**

**MSS**
1.  User requests to list all To Do's
2.  Lifebook shows a list of To Do's
3.  User requests to perform an action on a specific To Do from the list
4.  Lifebook performs action on To Do.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Lifebook shows an error message.

      Use case resumes at step 2.

* 3b. The given action does not exist.

    * 3b1. Lifebook shows an error message.

     Use case resumes at step 2.

* 3c. The user marks a recurring task as done.

    * 3c1. Lifebook will automatically add a new task with the same details, with a new deadline given by the recurrence.

**Use case: Filter items due on a specific date/time**

**MSS**

1.  User requests to filter items due by/before a specified date/time
2.  Lifebook shows a list of items that fulfil the requirement.

    Use case ends.

**Extensions**

* 1a. The given index is invalid.

    * 1a1. Lifebook shows an error message.

      Use case restarts at step 1.

* 1b. The given date/time format is invalid.

    * 1b1. Lifebook shows an error message.

        Use case restarts at step 1.

* 2a. The list is empty.

  Use case ends.

**Use case: Add or remove a collaborative link**

**MSS**

1. User requests a task to be given a link (GitHub Repo, Google Drive, etc).
2. Lifebook acknowledges the request by showing the requested task.
3. User requests to bind the link with the task.
4. Lifebook performs action on the task.

      Use case ends.

**Extensions**

* 1a. The given index is invalid.

    * 1a1. Lifebook shows an error message.

      Use case restarts at step 1.

* 2a. The list is empty.

* 3a. The task already has a link.

    * 3a1. Lifebook requests permission to override the existing link.

      Use case ends.
**Use case: Store and retrieve a meeting link**

**MSS**

1. User requests a task to be given a meeting link (Teams, Zoom, etc).
2. Lifebook acknowledges the request by attaching a link to the task.
3. User later requests to view meeting links associated with the task.
4. Lifebook shows the links associated with the task.

      Use case ends.

**Extensions**

* 1a. The given index is invalid.

    * 1a1. Lifebook shows an error message.

      Use case restarts at step 1.

* 2a. The list is empty.

* 3a. The task already has a link.

    * 3a1. Lifebook requests permission to override the existing link.

      Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Commands should be intuitive so that users can quickly remember the commands.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
