package com.example.fincare_uat;

import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.Log;
import android.view.View;

public class DragHelperCallback extends Callback{

    private int lastDraggingState;
    private int topBorderDraggableContainer;
    private DragtoClose dragToClose = null;
    private View draggableContainer = null;



    public void onViewDragStateChanged(int state) {
        try {
            if (state != this.lastDraggingState) {
                if ((this.lastDraggingState == 1 || this.lastDraggingState == 2) && state == 0 && this.topBorderDraggableContainer == this.dragToClose.getDraggableRange()) {
                    this.dragToClose.closeActivity();
                }

                if (state == 1) {
                    this.dragToClose.onStartDraggingView();
                }

                this.lastDraggingState = state;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("release mode : " ,e.getMessage());
        }
    }

    public void onViewPositionChanged( View changedView, int left, int top, int dx, int dy) {
//        Intrinsics.checkParameterIsNotNull(changedView, "changedView");
        this.topBorderDraggableContainer = top;
        this.dragToClose.onViewPositionChanged();
    }

    public void onViewReleased(View releasedChild, float xVel, float yVel) {
//        Intrinsics.checkParameterIsNotNull(releasedChild, "releasedChild");
        if (this.topBorderDraggableContainer != 0 && this.topBorderDraggableContainer < this.dragToClose.getDraggableRange()) {
            boolean settleToClosed = false;
            int settleDestY;
            if (yVel > 800.0F) {
                settleToClosed = true;
            } else {
                settleDestY = (int)((float)this.dragToClose.getDraggableRange() * 0.5F);
                if (this.topBorderDraggableContainer > settleDestY) {
                    settleToClosed = true;
                }
            }

            settleDestY = settleToClosed ? this.dragToClose.getDraggableRange() : 0;
            this.dragToClose.smoothScrollToY(settleDestY);
        }
    }

    public int getViewVerticalDragRange( View child) {
//        Intrinsics.checkParameterIsNotNull(child, "child");
        return this.dragToClose.getDraggableRange();
    }


    public int clampViewPositionHorizontal(View child, int left, int dx) {
//        Intrinsics.checkParameterIsNotNull(child, "child");
        return child.getLeft();
    }
    public boolean tryCaptureView(View child, int pointerId) {
//        Intrinsics.checkParameterIsNotNull(child, "child");
        return child==draggableContainer;
    }

    public int clampViewPositionVertical( View child, int top, int dy) {
//        Intrinsics.checkParameterIsNotNull(child, "child");
        int topBound = this.dragToClose.getPaddingTop();
        int bottomBound = this.dragToClose.getDraggableRange();
        return Math.min(Math.max(top, topBound), bottomBound);
    }

    public DragHelperCallback(DragtoClose dragToClose,  View draggableContainer) {
//        Intrinsics.checkParameterIsNotNull(dragToClose, "dragToClose");
//        Intrinsics.checkParameterIsNotNull(draggableContainer, "draggableContainer");
        super();
        this.dragToClose = dragToClose;
        this.draggableContainer = draggableContainer;
        this.lastDraggingState = 0;
    }
}
