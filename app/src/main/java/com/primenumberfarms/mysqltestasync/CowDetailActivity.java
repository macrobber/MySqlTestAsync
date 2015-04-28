package com.primenumberfarms.mysqltestasync;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mack on 4/26/15.
 */
public class CowDetailActivity extends Activity{


    //private EditText Tag;
    private EditText Name;
    private EditText Brand;
    private EditText RegNumber;

    private String Tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow_details);

//        this.Tag = (EditText) this.findViewById(R.id.Tag);
        this.Name = (EditText) this.findViewById(R.id.Name);
        this.Brand = (EditText) this.findViewById(R.id.Brand);
        this.RegNumber = (EditText) this.findViewById(R.id.RegNumber);


//        this.Tag = getIntent().getStringArrayExtra("Tag");  // When it craps....fix HERE>>>>>>>>>>>>>>>>>>>>>>
        this.Tag = getIntent().getStringExtra("Tag");

        if(this.Tag != null)
        {
            // we know we passed a tag....like...maybe???
            new GetCowDetails().execute(new ApiConnector());
        }



    }

    private class GetCowDetails extends AsyncTask<ApiConnector, Long, JSONArray>
    {
        @Override
        protected JSONArray doInBackground(ApiConnector...params) {
            // This is executed in the background thread
            return params[0].GetCowDetails(Tag);
        }

        @Override
        protected void onPostExecute(JSONArray jArray) {
            // once do in background is done - it sends the result to the main thread...here
//            JSONObject cow = jArray.getJSONObject(0);
            try
            {
                JSONObject cow = jArray.getJSONObject(0);
                Name.setText(cow.getString("Name"));
                Brand.setText(cow.getString("Brand"));
                RegNumber.setText(cow.getString("RegNumber"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

    }

}
