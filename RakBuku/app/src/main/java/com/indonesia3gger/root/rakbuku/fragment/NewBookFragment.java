package com.indonesia3gger.root.rakbuku.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.indonesia3gger.root.rakbuku.R;
import com.indonesia3gger.root.rakbuku.view.LoadMoreListView;

import java.util.LinkedList;

public class NewBookFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private LoadMoreListView lmlv;
    private SwipeRefreshLayout srl;
    private LinkedList<String> l_list;
    private ArrayAdapter<String> a_adapter;
    private int MaxPage = 5;
    private int currentPage = 0;

    public NewBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_book, container, false);
    }

    private void populateDefaultData(){
        String[] androidVersion = new String[]{
                "Not Apple", "Not Blackberry",
                "Cupcake", "Donut",
                "Eclair", "Froyo", "Gingerbread",
                "Honeycomb", "Ice cream sandwich",
                "Jelly Bean", "Kitkat", "Lollipop",
                "M Preview", "N (Coming soon on 2016)"
        };

        for (int i = 0; i < androidVersion.length; i++){
            l_list.add(androidVersion[i]);
        }

        a_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                android.R.id.text1, l_list);
        lmlv.setAdapter(a_adapter);
    }

    @Override
    public void onActivityCreated(Bundle SavedInstanceState) {
        super.onActivityCreated(SavedInstanceState);

        // Inflate the layout for this fragment
        lmlv = getView().findViewById(R.id.lv_item_new_book);
        srl = getView().findViewById(R.id.swipe_main_new_book);
        l_list = new LinkedList<>();

        populateDefaultData();

        //listener untuk loadmore
        lmlv.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (currentPage < MaxPage) {
                    new FakeLoadmoreAsync().execute();
                } else {
                    lmlv.onLoadMoreComplete();
                }
            }
        });

        srl.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {
        new FakePullRefreshAsync().execute();
    }

    //Background proses palsu untuk melakukan proses delay dan append data di bagian bawah list
    private class FakeLoadmoreAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(4000);
            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            populateLoadmoreData();
            currentPage +=1;
        }
    }

    private void populateLoadmoreData() {
        String loadmoreText = "Added on loadmore";
        for (int i = 0; i < 10; i++){
            l_list.addLast(loadmoreText);
        }
        a_adapter.notifyDataSetChanged();
        lmlv.onLoadMoreComplete();
        lmlv.setSelection(l_list.size() - 11);
    }

    //Background proses palsu untuk melakukan proses delay dan append data di bagian atas list
    private class FakePullRefreshAsync extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(4000);
            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            populatePullRefreshData();
        }
    }

    private void populatePullRefreshData() {
        String swipePullRefreshText = "Added after swipe layout";
        for (int i = 0; i < 5; i++){
            l_list.addFirst(swipePullRefreshText);
        }
        srl.setRefreshing(false);
        a_adapter.notifyDataSetChanged();
        lmlv.setSelection(0);

    }


}
