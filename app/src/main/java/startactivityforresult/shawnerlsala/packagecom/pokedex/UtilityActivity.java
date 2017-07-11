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

public class UtilityActivity extends AppCompatActivity {

    String JsonURLUtility = "http://pokeapi.co/api/v2/language/";
    private ArrayList utility;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view3);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


        utility = new ArrayList<>();
        sendRequest();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pokemon:
                Toast.makeText(this, "Pokemon selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent4 = new Intent(UtilityActivity.this, MainActivity.class);
                UtilityActivity.this.startActivity(intent4);
                break;
            case R.id.action_items:
                Toast.makeText(this, "Items selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(UtilityActivity.this, ItemActivity.class);
                UtilityActivity.this.startActivity(intent);
                break;
            case R.id.action_moves:
                Toast.makeText(this, "moves selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent1 = new Intent(UtilityActivity.this, MoveActivity.class);
                UtilityActivity.this.startActivity(intent1);
                break;
            case R.id.action_locations:
                Toast.makeText(this, "location selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent2 = new Intent(UtilityActivity.this, LocationActivity.class);
                UtilityActivity.this.startActivity(intent2);
                break;
            case R.id.action_utility:
                Toast.makeText(this, "utility selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent3 = new Intent(UtilityActivity.this, UtilityActivity.class);
                UtilityActivity.this.startActivity(intent3);
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

    JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURLUtility, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Inside on response", "true");
                        JSONArray obj = response.getJSONArray("results");

                        for (int init = 0; init < obj.length(); init++) {
                            JSONObject tempObj = obj.getJSONObject(init);
                            utility.add(tempObj.getString("name"));
                        }

                        RecyclerView.Adapter adapter = new DataAdapter(utility);
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
