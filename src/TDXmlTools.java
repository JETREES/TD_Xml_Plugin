import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.janusresearch.tdXmlPlugin.toolWindow.XmlConsole;

public class TDXmlTools extends AbstractProjectComponent{
    private Project project;

    protected TDXmlTools(Project p) {
        super(p);
        project = p;
    }

    @Override
    public void projectOpened() {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        ToolWindow toolWindow = toolWindowManager.registerToolWindow("Xml Console", false, ToolWindowAnchor.BOTTOM);
        XmlConsole xmlConsole = new XmlConsole();
        xmlConsole.createToolWindowContent(project, toolWindow);
    }
}
