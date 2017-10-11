package com.example.utkarshtiwari.booklisting.activity;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.utkarshtiwari.booklisting.R;
import com.example.utkarshtiwari.booklisting.adapters.RecyclerViewAdapter;
import com.example.utkarshtiwari.booklisting.models.Book;
import com.example.utkarshtiwari.booklisting.utils.BookLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {

    private static final String GOOGLE_BOOKS_API_BASE_QUERY =
            "https://www.googleapis.com/books/v1/volumes?q=";

    private static final int BOOK_REQUEST_LOADER = 1;


    private FloatingSearchView searchBarView;
    private String mLastQuery = "";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter bookAdapter;

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


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ArrayList<Book> searchResult = new ArrayList<Book>();
        bookAdapter = new RecyclerViewAdapter(this, searchResult);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookAdapter);
        setupSearchBar();
    }

    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(MainActivity.this, args.getString("request_url"));
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Book>> loader, ArrayList<Book> data) {
        setSearchLabelVisibility(data.size() == 0);
        if (data.size() == 0) {
            Toast.makeText(this, this.getResources().getString(R.string.no_books_found), Toast.LENGTH_LONG).show();
        } else {
            bookAdapter.updateAdapterData(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Book>> loader) {
        bookAdapter.updateAdapterData(new ArrayList<Book>());
        setSearchLabelVisibility(true);
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
                Log.d("LISTENER", query);
                String requestQuery = GOOGLE_BOOKS_API_BASE_QUERY + query;
                Bundle args = new Bundle();
                args.putString("request_url", requestQuery);
//                getSupportLoaderManager().
                getSupportLoaderManager().restartLoader(BOOK_REQUEST_LOADER, args, MainActivity.this).forceLoad();
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
