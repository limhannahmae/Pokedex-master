package startactivityforresult.shawnerlsala.packagecom.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ShawnErl on 06/07/2017.
 */

public class LocationActivity extends AppCompatActivity {

    String JsonURLLocation = "http://pokeapi.co/api/v2/location/";
    private ArrayList location;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view2);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


        location = new ArrayList<>();
        sendRequest();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_pokemon:
                Toast.makeText(this, "Pokemon selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(LocationActivity.this, MainActivity.class);
                LocationActivity.this.startActivity(intent);
                break;
            // action with ID action_settings was selected
            case R.id.action_items:
                Toast.makeText(this, "Items selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent4 = new Intent(LocationActivity.this, ItemActivity.class);
                LocationActivity.this.startActivity(intent4);
                break;
            case R.id.action_moves:
                Toast.makeText(this, "moves selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent1 = new Intent(LocationActivity.this, MoveActivity.class);
                LocationActivity.this.startActivity(intent1);
                break;
            case R.id.action_locations:
                Toast.makeText(this, "location selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent2 = new Intent(LocationActivity.this, LocationActivity.class);
                LocationActivity.this.startActivity(intent2);
                break;
            case R.id.action_utility:
                Toast.makeText(this, "utility selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent3 = new Intent(LocationActivity.this, UtilityActivity.class);
                LocationActivity.this.startActivity(intent3);
                break;
            default:
                break;
        }

        return true;
    }


    private void sendRequest() {
        RequestQueueSingleton.getInstance(this).add(obreq);
        Log.d("Inside sendRequest", "true");
    }

    JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURLLocation, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Inside on response", "true");
                        JSONArray obj = response.getJSONArray("results");

                        for (int init = 0; init < obj.length(); init++) {
                            JSONObject tempObj = obj.getJSONObject(init);
//                            Move mMove = new Move();
//                            mMove.setmName(tempObj.getString("name"));
                            location.add(tempObj.getString("name"));
//                            Toast.makeText(ItemActivity.this, tempObj.getString("name"), Toast.LENGTH_SHORT).show();
                        }

                        RecyclerView.Adapter adapter = new DataAdapter(location);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }
    );
}
