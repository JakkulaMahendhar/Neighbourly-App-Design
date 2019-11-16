package com.example.fincare_uat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.HashMap;

public class DragtoClose extends FrameLayout {
    private DragListener listener;
    private boolean finishActivity;
    private View draggableContainer;
    private View draggableView;
    private int draggableContainerTop;
    private int draggableContainerLeft;
    private ViewDragHelper dragHelper;
    private int verticalDraggableRange;
    private boolean uiBlocked;
    private static final float DRAG_SENSITIVITY = 1.0F;
    public static final float SPEED_THRESHOLD_TO_CLOSE = 800.0F;
    public static final float HEIGHT_THRESHOLD_TO_CLOSE = 0.5F;
    private int draggableContainerId;
    private int draggableViewId;
    private boolean closeOnClick;
    private HashMap findViewCache;




    public DragtoClose(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    protected void onFinishInflate() {
        super.onFinishInflate();
        this.initViews();
        this.initViewDragHelper();
    }

    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        this.verticalDraggableRange = h;
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
//        Intrinsics.checkParameterIsNotNull(event, "event");
        if (this.uiBlocked) {
            return true;
        } else {
            boolean handled = false;
            ViewDragHelper var10000;
            if (this.isEnabled()) {
                var10000 = this.dragHelper;
                if (var10000 == null) {
//                    Intrinsics.throwUninitializedPropertyAccessException("dragHelper");
                }

                boolean var3;
                label42: {
                    if (var10000.shouldInterceptTouchEvent(event)) {
                        var10000 = this.dragHelper;
                        if (var10000 == null) {
//                            Intrinsics.throwUninitializedPropertyAccessException("dragHelper");
                        }

                        View var10001 = this.draggableView;
                        if (var10001 == null) {
//                            Intrinsics.throwUninitializedPropertyAccessException("draggableView");
                        }

                        if (var10000.isViewUnder(var10001, (int)event.getX(), (int)event.getY())) {
                            var3 = true;
                            break label42;
                        }
                    }

                    var3 = false;
                }

                handled = var3;
            } else {
                var10000 = this.dragHelper;
                if (var10000 == null) {
//                    Intrinsics.throwUninitializedPropertyAccessException("dragHelper");
                }

                var10000.cancel();
            }

            return handled || super.onInterceptTouchEvent(event);
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent event) {
//        Intrinsics.checkParameterIsNotNull(event, "event");
        if (this.uiBlocked) {
            return true;
        } else {
            ViewDragHelper var10000 = this.dragHelper;
            if (var10000 == null) {
//                Intrinsics.throwUninitializedPropertyAccessException("dragHelper");
            }

            var10000.processTouchEvent(event);
            View var10001 = this.draggableView;
            if (var10001 == null) {
//                Intrinsics.throwUninitializedPropertyAccessException("draggableView");
            }

            return this.isViewTouched(var10001, (int)event.getX(), (int)event.getY());
        }
    }

    public void computeScroll() {
        ViewDragHelper var10000 = this.dragHelper;
        if (var10000 == null) {
//            Intrinsics.throwUninitializedPropertyAccessException("dragHelper");
        }

        if (var10000.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }

    }

    public final int getDraggableViewId() {
        return this.draggableViewId;
    }

    public final void setDraggableViewId(@IdRes int draggableViewId) {
        this.draggableViewId = draggableViewId;
        this.invalidate();
        this.requestLayout();
    }

    public final int getDraggableContainerId() {
        return this.draggableContainerId;
    }

    public final void setDraggableContainerId(@IdRes int draggableContainerId) {
        this.draggableContainerId = draggableContainerId;
        this.invalidate();
        this.requestLayout();
    }

    public final boolean isFinishActivity() {
        return this.finishActivity;
    }

    public final void setFinishActivity(boolean finishActivity) {
        this.finishActivity = finishActivity;
    }

    public final boolean isCloseOnClick() {
        return this.closeOnClick;
    }

    public final void setCloseOnClick(boolean closeOnClick) {
        if (closeOnClick) {
            View var10001 = this.draggableView;
            if (var10001 == null) {
//                Intrinsics.throwUninitializedPropertyAccessException("draggableView");
            }

            this.initOnClickListener(var10001);
        } else {
            View var10000 = this.draggableView;
            if (var10000 == null) {
//                Intrinsics.throwUninitializedPropertyAccessException("draggableView");
            }

            var10000.setOnClickListener((OnClickListener)null);
        }

        this.closeOnClick = closeOnClick;
    }

    public final void setDragListener(DragListener listener) {
//        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listener = listener;
    }

    public final void closeDraggableContainer() {
        this.uiBlocked = true;
        View var10001 = this.draggableContainer;
//        if (var10001 == null) {
//            Intrinsics.throwUninitializedPropertyAccessException("draggableContainer");
//        }

        this.slideViewTo(var10001, this.getPaddingLeft() + this.draggableContainerLeft, this.verticalDraggableRange);
    }

    public final void openDraggableContainer() {
        View var10001 = this.draggableContainer;
        if (var10001 == null) {
//            Intrinsics.throwUninitializedPropertyAccessException("draggableContainer");
        }

        this.slideViewTo(var10001, this.getPaddingLeft() + this.draggableContainerLeft, this.getPaddingTop() + this.draggableContainerTop);
        this.uiBlocked = false;
    }


    public final void onStartDraggingView() {
        DragListener var10000 = this.listener;
        if (var10000 != null) {
            var10000.onStartDraggingView();
        }

    }


    private final void initializeAttributes(AttributeSet attrs) {
        Context var10000 = this.getContext();
//        Intrinsics.checkExpressionValueIsNotNull(var10000, "context");
        TypedArray array = var10000.getTheme().obtainStyledAttributes(attrs, R.styleable.DragToClose, 0, 0);

        try {
            this.draggableViewId = array.getResourceId(R.styleable.DragToClose_dragtoclose_draggableView, -1);
            this.draggableContainerId = array.getResourceId(R.styleable.DragToClose_dragtoclose_draggableContainer, -1);
            this.finishActivity = array.getBoolean(R.styleable.DragToClose_dragtoclose_finishActivity, true);
            this.closeOnClick = array.getBoolean(R.styleable.DragToClose_dragtoclose_closeOnClick, false);
            if (this.draggableViewId == -1 || this.draggableContainerId == -1) {
//                throw (Throwable)(new IllegalArgumentException("draggableView and draggableContainer attributes are required."));
            }

            this.uiBlocked = false;
        } finally {
            array.recycle();
        }

    }

    private final boolean isViewTouched(View view, int x, int y) {
        int[] viewLocation = new int[2];
        view.getLocationOnScreen(viewLocation);
        int[] parentLocation = new int[2];
        this.getLocationOnScreen(parentLocation);
        int screenX = parentLocation[0] + x;
        int screenY = parentLocation[1] + y;
        return screenX >= viewLocation[0] && screenX < viewLocation[0] + view.getWidth() && screenY >= viewLocation[1] && screenY < viewLocation[1] + view.getHeight();
    }



    private final void initViewDragHelper() {
        ViewGroup var10001 = (ViewGroup)this;
        View var10006 = this.draggableContainer;
        DragHelperCallback var10003 = new DragHelperCallback(this, var10006);
//        if (var10006 == null) {
//            Intrinsics.throwUninitializedPropertyAccessException("draggableContainer");
//        }

        ViewDragHelper var1 = ViewDragHelper.create(var10001, 1.0F, (ViewDragHelper.Callback)var10003);
//        Intrinsics.checkExpressionValueIsNotNull(var1, "ViewDragHelper.create(th…gableContainer)\n        )");
        this.dragHelper = var1;
    }

    public DragtoClose(Context context) {
//        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context);
        this.draggableContainerId = -1;
        this.draggableViewId = -1;
    }

    public DragtoClose(Context context, AttributeSet attrs) {
//        Intrinsics.checkParameterIsNotNull(context, "context");
//        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        super(context, attrs);
        this.draggableContainerId = -1;
        this.draggableViewId = -1;
        this.initializeAttributes(attrs);
    }

    public DragtoClose(Context context, AttributeSet attrs, int defStyleAttr) {
//        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context, attrs, defStyleAttr);
        this.draggableContainerId = -1;
        this.draggableViewId = -1;
        this.initializeAttributes(attrs);
    }


    public View findCachedViewById(int var1) {
        if (this.findViewCache == null) {
            this.findViewCache = new HashMap();
        }

        View var2 = (View)this.findViewCache.get(var1);
        if (var2 == null) {
            var2 = this.findViewById(var1);
            this.findViewCache.put(var1, var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this.findViewCache != null) {
            this.findViewCache.clear();
        }

    }


    private final void initViews() {
        View var10001 = this.findViewById(this.draggableContainerId);
        if (var10001 != null) {
            this.draggableContainer = var10001;
            var10001 = this.draggableContainer;
//            if (var10001 == null) {
//                Intrinsics.throwUninitializedPropertyAccessException("draggableContainer");
//            }

            this.draggableContainerTop = var10001.getTop();
            var10001 = this.draggableContainer;
//            if (var10001 == null) {
//                Intrinsics.throwUninitializedPropertyAccessException("draggableContainer");
//            }

            this.draggableContainerLeft = var10001.getLeft();
            var10001 = this.findViewById(this.draggableViewId);
            if (var10001 != null) {
                this.draggableView = var10001;
                if (this.closeOnClick) {
                    var10001 = this.draggableView;
//                    if (var10001 == null) {
//                        Intrinsics.throwUninitializedPropertyAccessException("draggableView");
//                    }

                    this.initOnClickListener(var10001);
                }

            } else {
               // throw (Throwable)(new IllegalArgumentException("draggableView not found!"));
            }
        } else {
           // throw (Throwable)(new IllegalArgumentException("draggableContainer not found!"));
        }
    }

    private final void initOnClickListener(View clickableView) {
        clickableView.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                DragtoClose.this.closeDraggableContainer();
            }
        }));
    }




    private final void slideViewTo(View view, int left, int top) {
        ViewDragHelper var10000 = this.dragHelper;
//        if (var10000 == null) {
//            Intrinsics.throwUninitializedPropertyAccessException("dragHelper");
//        }

        var10000.smoothSlideViewTo(view, left, top);
        this.invalidate();
    }

    public final void closeActivity() {
        try {
            DragListener var10000 = this.listener;
            if (var10000 != null) {
                var10000.onViewCosed();
            }

            if (this.finishActivity) {
                Context var2 = this.getContext();
            if (var2 == null) {
                throw new ClassCastException("null cannot be cast to non-null type android.app.Activity");
            }

                Activity activity = (Activity) var2;
                activity.finish();
                //activity.overridePendingTransition(0, -7);

            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("only debug mode " ,e.getMessage());
        }

    }


    public final void onViewPositionChanged() {
        float verticalDragOffset = this.getVerticalDragOffset();
        this.changeDragViewViewAlpha(verticalDragOffset);
        DragListener var10000 = this.listener;
        if (var10000 != null) {
            var10000.onDragging(verticalDragOffset);
        }

    }

    public final void changeDragViewViewAlpha(float verticalDragOffset) {
        View var10000 = this.draggableContainer;
//        if (var10000 == null) {
//            Intrinsics.throwUninitializedPropertyAccessException("draggableContainer");
//        }

        var10000.setAlpha((float)1 - verticalDragOffset);
    }

    private final float getVerticalDragOffset() {
        View var10000 = this.draggableContainer;
//        if (var10000 == null) {
//            //Intrinsics.throwUninitializedPropertyAccessException("draggableContainer");
//        }

        return (float)Math.abs(var10000.getTop()) / (float)this.getHeight();
    }

    public final int getDraggableRange() {
        return this.verticalDraggableRange;
    }

    public final void smoothScrollToY(int settleDestY) {
        ViewDragHelper var10000 = this.dragHelper;
//        if (var10000 == null) {
//            Intrinsics.throwUninitializedPropertyAccessException("dragHelper");
//        }

        if (var10000.settleCapturedViewAt(this.getPaddingLeft(), settleDestY)) {
            ViewCompat.postInvalidateOnAnimation((View)this);
        }

    }

}
