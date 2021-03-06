package startactivityforresult.shawnerlsala.packagecom.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String JsonURLPokemon = "http://pokeapi.co/api/v2/pokemon/";


    private GridView mGridViewPokemons;
    private PokemonListAdapter pokemonListAdapter;
    private List<Pokemon> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridViewPokemons = (GridView) findViewById(R.id.gridView_pokemons);
        pokemonList = new ArrayList<Pokemon>();
        pokemonListAdapter = new PokemonListAdapter(MainActivity.this, pokemonList);
        mGridViewPokemons.setAdapter(pokemonListAdapter);

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
            case R.id.action_pokemon:
                Toast.makeText(this, "Pokemon selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent4 = new Intent(MainActivity.this, MainActivity.class);
                MainActivity.this.startActivity(intent4);
                break;
            case R.id.action_items:
                Toast.makeText(this, "Items selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.action_moves:
                Toast.makeText(this, "moves selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent1 = new Intent(MainActivity.this, MoveActivity.class);
                MainActivity.this.startActivity(intent1);
                break;
            case R.id.action_locations:
                Toast.makeText(this, "location selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent2 = new Intent(MainActivity.this, LocationActivity.class);
                MainActivity.this.startActivity(intent2);
                break;
            case R.id.action_utility:
                Toast.makeText(this, "utility selected", Toast.LENGTH_SHORT)
                        .show();
                Intent intent3 = new Intent(MainActivity.this, UtilityActivity.class);
                MainActivity.this.startActivity(intent3);
                break;
            default:
                break;
        }

        return true;
    }

    private void sendRequest() {
        RequestQueueSingleton.getInstance(this).add(obreq);
    }

    JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURLPokemon, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray obj = response.getJSONArray("results");

                        for (int init = 0; init < obj.length(); init++) {
                            JSONObject tempObj = obj.getJSONObject(init);
                            Pokemon poke = new Pokemon();
                            poke.setPokemonName(tempObj.getString("name"));
                            poke.setPokemonImageURL(tempObj.getString("url"));
                            pokemonList.add(poke);
                        }

                        pokemonListAdapter.notifyDataSetChanged();
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
