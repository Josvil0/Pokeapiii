package com.example.pokeapi;




import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.pokeapi.model.Pokemon;
import com.example.pokeapi.model.PokemonList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PokemonViewModel extends ViewModel {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private MutableLiveData<Pokemon> pokemonLiveData;
    private MutableLiveData<PokemonList> pokemonListLiveData;


    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private PokeApiService pokeApiService = retrofit.create(PokeApiService.class);

    public PokemonViewModel() {
        pokemonLiveData = new MutableLiveData<>();
        pokemonListLiveData = new MutableLiveData<>();
    }


    public LiveData<Pokemon> getPokemonLiveData() {
        return pokemonLiveData;
    }

    public LiveData<PokemonList> getPokemonListLiveData() {
        return pokemonListLiveData;
    }


    public void fetchPokemonById(int id) {
        pokeApiService.getPokemonById(String.valueOf(id)).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pokemonLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
    }


    public void fetchPokemonList(int limit, int offset) {
        pokeApiService.getPokemonList(limit, offset).enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pokemonListLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {

            }
        });
    }
}

