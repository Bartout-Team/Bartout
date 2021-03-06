package ch.zhaw.bartout.domain.bartour.chronicle;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.bartout.R;
import se.walkercrou.places.Place;

/**
 * The class holds ATM-visiting-events.
 */
public class ATMLocationChronicleEvent extends LocationChronicleEvent {

    public ATMLocationChronicleEvent() {}

    public ATMLocationChronicleEvent(Place place) {
        super(place);
    }

    @Override
    public String getDisplayName() {
        return "Bank besucht: <b>" + getLocationName() + "</b>";
    }

    @Override
    protected String convertType(List<String> gTypes) {
        ArrayList<String> types = new ArrayList<String>();
        if (gTypes.contains("atm")) {
            types.add("Bankomat");
        }
        if (gTypes.contains("bank")) {
            types.add("Bank");
        }
        return types.toString().replace("[", "").replace("]", "");
    }

    @Override
    public View getView(Context context){
        View view = super.getView(context);

        ImageView img = (ImageView) view.findViewById(R.id.image_icon);
        img.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_money_pin));

        return view;
    }
}
