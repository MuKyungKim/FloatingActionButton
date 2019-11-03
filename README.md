[![](https://jitpack.io/v/martipello/FloatingActionButton.svg)](https://jitpack.io/#martipello/FloatingActionButton)

# THIS IS A FORK OF CLANS FLOATING ACTION BUTTON 
This library is Clans floating action button library which I have forked in order to add the ability to open menus left and right, and to make the button take into account default styles. I wont be submitting a pull request as labels arent quite right
# FloatingActionButton
Everything clans did with this library works I haven't broken anything in his implementation, but I have added left and right opening and labels do not work for this,

for full details about this library, how it works, how to style it, and licences please see clans library here https://github.com/Clans/FloatingActionButton

just add it to your project like this 

implementation 'com.github.martipello:FloatingActionButton:v2.0'

and check out the examples, again all done by clans, with very slight tweak by me

change app:menu_openDirection="left" to up, down, left or right

example

<com.github.sealstudios.fab.FloatingActionMenu
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  android:id="@+id/speak_fab"
  app:layout_anchor="@id/sentence_container"
  app:layout_anchorGravity="bottom|right|end"
  app:menu_icon="@drawable/ic_play_arrow_white_24dp"
  app:fab_colorNormal="@color/primary_blue"
  app:menu_openDirection="left"
  android:layout_margin="@dimen/fab_margin">

  <com.github.sealstudios.fab.FloatingActionButton
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  app:fab_size="mini"
  app:menu_icon="@drawable/ic_play_arrow_white_24dp"
  app:fab_colorNormal="@color/primary_blue"/>

  <com.github.sealstudios.fab.FloatingActionButton
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  app:fab_size="mini"
  app:menu_icon="@drawable/ic_play_arrow_white_24dp"
  app:fab_colorNormal="@color/primary_blue"/>

  <com.github.sealstudios.fab.FloatingActionButton
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  app:fab_size="mini"
  app:menu_icon="@drawable/ic_play_arrow_white_24dp"
  app:fab_colorNormal="@color/primary_blue"/>

  </com.github.sealstudios.fab.FloatingActionMenu>

