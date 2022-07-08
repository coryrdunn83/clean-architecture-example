# clean-architecture-example
 An example project to demonstrate the use of clean architecture in a Kotlin/Jetpack Compose Android project
 
# Features
 Users of the app can view a list of notes as well as sort the list based on Title, Date Created or Color in both Ascending and Descending order.
 
 Users can add new notes to the list by selecting the floating action button which will navigate to a blank note where a user can add a title and content to the note as well as select a color for the note. Users can then save the new note and will be returned to the list which will now include the newly saved note.
 
 Users can edit previously created notes by clicking on a note in the list. This action will navigate to a note screen that is populated with the note's data and can be changed then saved.
 
 Users can delete previously created notes by clicking on the delete icon on any particular note. This will remove the note from the list and show a snackbar informing the user that the note has been deleted as well as provide an option to 'undo' the action.

# Technologies
- Kotlin
- Kotlin Coroutines
- Kotlin Flow
- Jetpack Compose
- Room
- Hilt

