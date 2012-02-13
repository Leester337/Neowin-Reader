package com.neowinreader.app;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;



public class ArticleAdapter extends ArrayAdapter<Article> {
	int resource;
    String response;
    Context context;
    //Initialize adapter
    public ArticleAdapter(Context context, int resource, List<Article> items) {
        super(context, resource, items);
        this.resource=resource;
 
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout alertView;
        //Get the current alert object
        Article art = getItem(position);
 
        //Inflate the view
        if(convertView==null)
        {
            alertView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, alertView, true);
        }
        else
        {
            alertView = (LinearLayout) convertView;
        }
        //Get the text boxes from the listitem.xml file
        TextView alertTitle =(TextView)alertView.findViewById(R.id.articleTitle);
        TextView alertDesc =(TextView)alertView.findViewById(R.id.articleDesc);
        alertDesc.setSingleLine();
        TruncateAt where = TruncateAt.END;
		alertDesc.setEllipsize(where);
		alertTitle.setLineSpacing(0, (float) 1.3);
		int color = Color.GRAY;
        alertDesc.setTextColor(color);
        alertTitle.setTextSize(20);
        alertTitle.setTextColor(Color.WHITE);
        //Assign the appropriate data from our alert object above
        alertTitle.setText(art.getTitle());
        alertDesc.setText(art.getDescription());
        
        //TextAppearance contextColor = TextAppeareance.
        //alertDesc.setTextAppearance(contextColor, residInt);
 
        return alertView;
    }
}
