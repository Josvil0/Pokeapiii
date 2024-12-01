
package com.example.pokeapi;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pokeapi.model.Pokemon;
import java.util.List;

public class PokemonFragment extends Fragment {

    private PokemonViewModel pokemonViewModel;
    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pokemon, container, false);


        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pokemonAdapter = new PokemonAdapter();
        recyclerView.setAdapter(pokemonAdapter);


        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);


        pokemonViewModel.getPokemonListLiveData().observe(getViewLifecycleOwner(), pokemonList -> {
            if (pokemonList != null) {

                List<Pokemon> pokemonListData = pokemonList.getResults();
                pokemonAdapter.setPokemonList(pokemonListData);
            } else {
                Toast.makeText(getContext(), "Error al obtener los Pokémon", Toast.LENGTH_SHORT).show();
            }
        });


        pokemonViewModel.fetchPokemonList(400, 0);

        return root;
    }
}

