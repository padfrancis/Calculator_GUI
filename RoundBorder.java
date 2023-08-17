package Calculator_GUI;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class RoundBorder extends AbstractBorder
{
    private final Color color;
    private final int thickness;
    private final int arcWidth;
    private final int arcHeight;

    public RoundBorder(Color color, int thickness, int arcWidth, int arcHeight)
    {
        this.color = color;
        this.thickness = thickness;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        Graphics2D g3 = (Graphics2D) g.create();
        g3.setColor(color);
        g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g3.setStroke(new BasicStroke(thickness));
        g3.drawRoundRect(x, y, width - 1, height - 1, arcWidth, arcHeight);

        g3.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c)
    {
        return new Insets(thickness, thickness, thickness, thickness);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets)
    {
        insets.left = insets.right = insets.top = insets.bottom = thickness;
        return insets;
    }
}
