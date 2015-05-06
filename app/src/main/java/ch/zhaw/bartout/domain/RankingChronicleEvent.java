package ch.zhaw.bartout.domain;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ch.zhaw.bartout.R;

/**
 * Created by Nico on 31.03.2015.
 */
public class RankingChronicleEvent extends ChronicleEvent {

    private Ranking ranking;

    public  RankingChronicleEvent(Ranking ranking){
        super();
        this.ranking = ranking;
    }

    public Ranking getRanking() {   return ranking; }

    public void setRanking(Ranking ranking) {   this.ranking = ranking; }

    @Override
    public String getDisplayName() {
        return "Ranking";
    }

    @Override
    public View getView(Context context){
        View view = super.getView(context);

        ImageView img = (ImageView) view.findViewById(R.id.image_icon);
        img.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_ranking));

        TextView txt = (TextView) view.findViewById(R.id.text_name);

        int i=1;
        for(RankingUser u : ranking.getRanking()){
            txt.append("\n   " + i + ". " + u.getUser().getName());
            i++;
        }

        return view;
    }
}
