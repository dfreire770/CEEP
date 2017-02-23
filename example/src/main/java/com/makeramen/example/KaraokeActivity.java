package com.makeramen.example;

import android.app.Activity;
import android.os.Bundle;

public class KaraokeActivity extends Activity //implements ActionBar.OnNavigationListener {
{
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

    /*getActionBar().setListNavigationCallbacks(
        ArrayAdapter.createFromResource(
            getActionBar().getThemedContext(),
            R.array.action_list,
            android.R.layout.simple_spinner_dropdown_item),
        this);
*/
    if (savedInstanceState == null) {
      getFragmentManager().beginTransaction()
          .replace(android.R.id.content, new KaraokeFragment())
          .commit();
    }

  }
//@Override
   /*public boolean onNavigationItemSelected(int itemPosition, long itemId) {

    KaraokeFragment newFragment;
    switch (itemPosition) {
      default:
      case 0:
        newFragment = MainFragment.getInstance(false);
        break;
      case 1:
        newFragment = MainFragment.getInstance(true);
        break;
    }

    getFragmentManager().beginTransaction()
        .replace(android.R.id.content, newFragment)
        .commit();

    return true;
  }*/
}
