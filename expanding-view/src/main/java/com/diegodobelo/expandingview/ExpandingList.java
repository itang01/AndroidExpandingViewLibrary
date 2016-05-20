/**
 Copyright (c) 2016, Diego Bezerra <diego.bezerra@gmail.com>
 Permission to use, copy, modify, and/or distribute this software for any purpose
 with or without fee is hereby granted, provided that the above copyright notice
 and this permission notice appear in all copies.
 THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
 REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND
 FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT,
 OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE,
 DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS
 ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 **/
package com.diegodobelo.expandingview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by diego on 5/9/16.
 */
public class ExpandingList extends ScrollView {
    /**
     * Member variable to hold the items.
     */
    private LinearLayout mContainer;

    /**
     * The constructor.
     * @param context The View Context.
     * @param attrs The attributes.
     */
    public ExpandingList(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContainer = new LinearLayout(context);
        mContainer.setOrientation(LinearLayout.VERTICAL);
        addView(mContainer);
    }

    /**
     * Method to add a new item.
     * @param item The ExpandingItem item.
     */
    private void addItem(ExpandingItem item) {
        mContainer.addView(item);
    }

    /**
     * Method to create and add a new item.
     * @param layoutId The item Layout.
     * @return The created item.
     */
    public ExpandingItem createNewItem(int layoutId) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ViewGroup item = (ViewGroup) inflater.inflate(layoutId, this, false);
        if (item instanceof ExpandingItem) {
            ExpandingItem expandingItem = (ExpandingItem) item;
            expandingItem.setParent(this);
            addItem(expandingItem);
            return expandingItem;
        }
        throw new RuntimeException("The layout id not an instance of com.diegodobelo.expandinganimlib.ExpandingItem");
    }

    /**
     * Scroll up to show sub items
     * @param delta The calculated amount to scroll up.
     */
    protected void scrollUpByDelta(int delta) {
        smoothScrollTo(0, getScrollY() + delta);
    }
}
