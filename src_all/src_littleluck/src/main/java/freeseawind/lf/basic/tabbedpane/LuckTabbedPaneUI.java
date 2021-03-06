package freeseawind.lf.basic.tabbedpane;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * <p>
 * TabbedPaneUI实现类，取消选项卡的弹簧效果和圆角阴影边框， 选项卡使用扁平化矩形绘制。
 * </p>
 *
 * <p>
 * TabbedPaneUI implementation class, cancel the tab spring effect and rounded
 * shadow border, tab Use flat rectangle Draw.
 * </p>
 *
 * @author freeseawind@github
 * @version 1.0
 *
 */
public class LuckTabbedPaneUI extends BasicTabbedPaneUI
{
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 4;
    public static final int EAST = 8;
    protected Color selectedColor;
    protected Color selectedShadow;

    public static ComponentUI createUI(JComponent c)
    {
        return new LuckTabbedPaneUI();
    }

    public void installUI(JComponent c)
    {
        super.installUI(c);
    }

    protected void installDefaults()
    {
        super.installDefaults();

        selectedColor = UIManager.getColor(LuckTabbedPaneUIBundle.SELECTEDCOLOR);

        selectedShadow = UIManager.getColor(LuckTabbedPaneUIBundle.SELECTEDSHADOW);
    }

    @Override
    protected void uninstallDefaults()
    {
        super.uninstallDefaults();

        selectedColor = null;

        selectedShadow = null;
    }

    protected void paintTab(Graphics g,
                            int tabPlacement,
                            Rectangle[] rects,
                            int tabIndex,
                            Rectangle iconRect,
                            Rectangle textRect)
    {
        super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
    }

    protected void paintTabBackground(Graphics g,
                                      int tabPlacement,
                                      int tabIndex,
                                      int x,
                                      int y,
                                      int w,
                                      int h,
                                      boolean isSelected)
    {

        g.setColor(!isSelected || selectedColor == null?
                tabPane.getBackgroundAt(tabIndex) : selectedColor);

        switch (tabPlacement)
        {
            case LEFT:

                g.fillRect(x, y + 1, w, h - 3);

                break;

            case RIGHT:

                g.fillRect(x, y + 1, w, h - 3);

                break;

            case TOP:

            case BOTTOM:

                g.fillRect(x + 2, y + 1, w - 2, h - 1);

                break;

            default:
                break;
        }
    }

    protected void paintTabBorder(Graphics g,
                                  int tabPlacement,
                                  int tabIndex,
                                  int x,
                                  int y,
                                  int w,
                                  int h,
                                  boolean isSelected)
    {
        if(!isSelected)
        {
            g.setColor(shadow);
        }
        else
        {
            g.setColor(selectedShadow);
        }

        switch (tabPlacement)
        {
            case LEFT:

                paintBorder(g, x, y + 1, w - 1, h - 2, NORTH +  SOUTH + WEST);

                break;

            case RIGHT:

                paintBorder(g, x, y + 1, w - 1, h - 2, NORTH +  SOUTH + EAST);

                break;

            case TOP:

                paintBorder(g, x + 1, y, w - 2, h, NORTH + WEST + EAST);

                break;

            case BOTTOM:

                paintBorder(g, x + 1, y + 1, w - 2, h, SOUTH + WEST + EAST);

                break;

            default:

                break;
        }
    }

    protected void paintContentBorderTopEdge(Graphics g,
                                             int tabPlacement,
                                             int selectedIndex,
                                             int x,
                                             int y,
                                             int w,
                                             int h)
    {
        if(tabPlacement == TOP)
        {
            g.setColor(UIManager.getColor(LuckTabbedPaneUIBundle.SHADOW));
            g.drawLine(x, y, x + w, y);
        }
    }

    protected void paintContentBorderLeftEdge(Graphics g,
                                              int tabPlacement,
                                              int selectedIndex,
                                              int x,
                                              int y,
                                              int w,
                                              int h)
    {
        if(tabPlacement == LEFT)
        {
            g.setColor(UIManager.getColor(LuckTabbedPaneUIBundle.SHADOW));
            g.drawLine(x, y, x, y + h);
        }
    }

    protected void paintContentBorderBottomEdge(Graphics g,
                                                int tabPlacement,
                                                int selectedIndex,
                                                int x,
                                                int y,
                                                int w,
                                                int h)
    {
        if(tabPlacement == BOTTOM)
        {
            g.setColor(UIManager.getColor(LuckTabbedPaneUIBundle.SHADOW));
            g.drawLine(x, y + h, x + w, y + h);
        }
    }

    protected void paintContentBorderRightEdge(Graphics g,
                                               int tabPlacement,
                                               int selectedIndex,
                                               int x,
                                               int y,
                                               int w,
                                               int h)
    {
        if(tabPlacement == RIGHT)
        {
            g.setColor(UIManager.getColor(LuckTabbedPaneUIBundle.SHADOW));
            g.drawLine(x + w, y, x + w, y + h);
        }
    }

    /**
     * <p>选项卡选中时绘制焦点虚线, LittleLuck取消了焦点绘制。</p>
     *
     * <p>Select the tab to draw the focus when the dotted line, LittleLuck canceled focus draw.</p>
     */
    protected void paintFocusIndicator(Graphics g,
                                       int tabPlacement,
                                       Rectangle[] rects,
                                       int tabIndex,
                                       Rectangle iconRect,
                                       Rectangle textRect,
                                       boolean isSelected)
    {
        // undo
    }

    /**
     * <p>选项卡选中时y坐标偏移量。</p>
     *
     * <p>y coordinate offset when the tab is selected.</p>
     */
    protected int getTabLabelShiftY(int tabPlacement,
                                    int tabIndex,
                                    boolean isSelected)
    {
        return 0;
    }

    /**
     * <p>选项卡选中时x坐标偏移量。</p>
     *
     * <p>x coordinate offset when the tab is selected.</p>
     */
    protected int getTabLabelShiftX(int tabPlacement,
                                    int tabIndex,
                                    boolean isSelected)
    {
        return 0;
    }

    /**
     * <p>绘制选项卡边框。</p>
     *
     * <p>Draw tab border.</p>
     *
     * @param g
     * @param x
     * @param y
     * @param width
     * @param height
     * @param rule
     */
    private void paintBorder(Graphics g,
                             int x,
                             int y,
                             int width,
                             int height,
                             int rule)
    {
        // draw top
        if((rule & NORTH) != 0)
        {
            g.drawLine(x, y, x + width, y);
        }

        // draw left
        if((rule & WEST) != 0)
        {
            g.drawLine(x, y, x, y + height - 1);
        }

        // draw bottom
        if((rule & SOUTH) != 0)
        {
            g.drawLine(x, y + height - 1, x + width, y + height - 1);
        }

        // draw right
        if((rule & EAST) != 0)
        {
            g.drawLine(x + width, y, x + width, y + height - 1);
        }
    }
}
