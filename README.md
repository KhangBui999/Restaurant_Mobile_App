# Sydney Foodie App
A mobile app that acts as a restaurant guide for Sydney.</br>
Completed as part of the INFS3634 course at UNSW - (zID: z5209606)</br>
For optimal use, please use a Pixel 3 (for handheld) or a Pixel C (for tablet) emulator.</br>
</br>
<h2>Application Features</h2>
<b>Basic Required Features</b></br>

* RecyclerView containing Restaurants with relevant info e.g. name, cuisine, location & rating
* DetailView contains relevant information of clicked restaurant with an image
</br>
<b>Additional Features</b></br>

* Supports both handheld and tablet devices
* RatingBar for both MainActivity and DetailActivity
* DetailView contains additional information such as address, phone number and website link
* Clicking address in DetailView opens Google Maps with location specified
* Clicking phone number in DetailView opens the Phone app with the number in the dial
* Clicking website link in DetailView opens the page on Google Chrome
* Back button for DetailActivity
* Additional options feature (located in MainActivity ActionBar) - opens Sorting dialog
* Sorting options include by rating or alphabetically (uses QuickSort algorithm)
* Animations for the RecyclerView when Activity is first open or list is refreshed
* 'Slide' transition animations for both MainActivity and DetailActivity (handheld only)
</br>
<h2>Screenshots</h2></br>
</br>
<h2>Notes</h2>

* Using Android Studio 3.6 (may not work on Android Studio 3.5)
* RecyclerView, CardView, ChipGroups, Chips, Animations & Transitions require dependencies
* Animations and transitions do not work before Android 5 (Lollipop)
* If error occurs, please check build.gradle to check if dependencies are installed
