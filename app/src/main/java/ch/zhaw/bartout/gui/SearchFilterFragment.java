package ch.zhaw.bartout.gui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.zhaw.bartout.R;

public class SearchFilterFragment extends DialogFragment {

    private static String ARG_FILTER_STRING = "ARG_FILTER_STRING";

    static SearchFilterFragment newInstance(String filter) {
        SearchFilterFragment f = new SearchFilterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FILTER_STRING, filter);
        f.setArguments(args);
        return f;
    }

    private static String PIPE_CHAR = "%7C";

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(String filter);
    }

    NoticeDialogListener listener;
    List selectedItems;
    String[] filters;
    AlertDialog dialog;

    public SearchFilterFragment(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        selectedItems = new ArrayList();
        filters = getResources().getStringArray(R.array.search_filters);
        String filter = getArguments().getString(ARG_FILTER_STRING);
        String[] filterArr = filter.split(PIPE_CHAR);
        boolean[] allSelected = new boolean[filters.length];

        for(String f : filterArr){
            int i = Arrays.asList(filters).indexOf(getDispName(f));
            selectedItems.add(i);
            allSelected[i] = true;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setPositiveButton(R.string.ok_text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String filter = generateFilter();
                        listener.onDialogPositiveClick(filter);
                    }
                })
                .setNegativeButton(R.string.cancel_text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setTitle(R.string.title_search_filter)
                .setMultiChoiceItems(R.array.search_filters, allSelected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            selectedItems.add(which);
                        } else if (selectedItems.contains(which)) {
                            selectedItems.remove(Integer.valueOf(which));
                        }
                        SearchFilterFragment.this.dialog.getButton(Dialog.BUTTON_POSITIVE).setEnabled(selectedItems.size() != 0);
                    }
                });
        dialog = builder.create();
        return dialog;
    }

    public void attatch(NoticeDialogListener listener){
        this.listener = listener;
    }

    private String generateFilter(){
        StringBuilder filterBuilder = new StringBuilder();
        for(int i=0; i<selectedItems.size(); i++){
            if(i!=0) filterBuilder.append(PIPE_CHAR);
            String dispName = filters[(int)selectedItems.get(i)];
            if(dispName.equals(getString(R.string.search_filter_bar))){
                filterBuilder.append(getString(R.string.search_filter_bar_name));
            }else if(dispName.equals(getString(R.string.search_filter_club))){
                filterBuilder.append(getString(R.string.search_filter_club_name));
            }else if(dispName.equals(getString(R.string.search_filter_restaurant))){
                filterBuilder.append(getString(R.string.search_filter_restaurant_name));
            }else if(dispName.equals(getString(R.string.search_filter_food))){
                filterBuilder.append(getString(R.string.search_filter_food_name));
            }else if(dispName.equals(getString(R.string.search_filter_atm))){
                filterBuilder.append(getString(R.string.search_filter_atm_name));
            }else if(dispName.equals(getString(R.string.search_filter_atm))){
                filterBuilder.append(getString(R.string.search_filter_bank_name));
            }
        }
        return filterBuilder.toString();
    }

    private String getDispName(String filterName){
        if(filterName.equals(getString(R.string.search_filter_bar_name))){
            return (getString(R.string.search_filter_bar));
        }else if(filterName.equals(getString(R.string.search_filter_club_name))){
            return (getString(R.string.search_filter_club));
        }else if(filterName.equals(getString(R.string.search_filter_restaurant_name))){
            return (getString(R.string.search_filter_restaurant));
        }else if(filterName.equals(getString(R.string.search_filter_food_name))){
            return (getString(R.string.search_filter_food));
        }else if(filterName.equals(getString(R.string.search_filter_atm_name))){
            return (getString(R.string.search_filter_atm));
        }else if(filterName.equals(getString(R.string.search_filter_atm_name))){
            return (getString(R.string.search_filter_bank));
        }
        return "";
    }
}
