package com.example.utkarshtiwari.booklisting.activity;

import android.content.res.Configuration;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.utkarshtiwari.booklisting.R;
import com.example.utkarshtiwari.booklisting.adapters.RecyclerViewAdapter;
import com.example.utkarshtiwari.booklisting.models.Book;
import com.example.utkarshtiwari.booklisting.utils.JSONParser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{

    public static final String GOOGLE_BOOKS_API_BASE_QUERY =
            "https://www.googleapis.com/books/v1/volumes?q=";

    private FloatingSearchView searchBarView;
    private String mLastQuery = "";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBarView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        searchBarView.setOnHomeActionClickListener(
                new FloatingSearchView.OnHomeActionClickListener() {
                    @Override
                    public void onHomeClicked() {
                        finish();
                    }
                });



        allItemList = jsonParser.getResponseData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ArrayList<Book> searchResult;
        searchResult = new ArrayList<Book>();
        RecyclerViewAdapter bookAdapter = new RecyclerViewAdapter(this, searchResult);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookAdapter);
        setupSearchBar();
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(MainActivity.this, );
    }
    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        bookAdapter.updateAdapterData(data);
    }
    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        bookAdapter.updateAdapterData(new ArrayList<Book>());
    }

    /**
     * Sets up listeners for search bar
     */
    public void setupSearchBar() {


        searchBarView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(final SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String query) {
                mLastQuery = query;

                String requestQuery = GOOGLE_BOOKS_API_BASE_QUERY + query;
                
                
            }
        });
    }




    /**
     * Updates search results in the recycler view
     *
     * @param searchResult
     */
    public void updateResults(ArrayList<Book> searchResult) {
        RecyclerViewAdapter adapter = (RecyclerViewAdapter) recyclerView.getAdapter();
        adapter.updateAdapterData(searchResult);
        adapter.notifyDataSetChanged();

        searchBarView.clearSuggestions();
        searchBarView.clearQuery();
        searchBarView.clearSearchFocus();
    }

    /**
     * Sets visibility of search label
     *
     * @param flag
     */
    public void setSearchLabelVisibility(boolean flag) {
        RelativeLayout searchLabel = (RelativeLayout) findViewById(R.id.search_label);
        int id = flag ? View.VISIBLE : View.INVISIBLE;
        searchLabel.setVisibility(id);
    }

    @Override
    public void onBackPressed() {
        if (searchBarView.isSearchBarFocused()) {
            searchBarView.clearSuggestions();
            searchBarView.clearQuery();
            searchBarView.clearSearchFocus();
            return;
        }
        finish();
    }
}
