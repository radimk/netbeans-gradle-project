package org.netbeans.gradle.project;

import java.awt.Image;
import java.beans.BeanInfo;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;

public final class NbIcons {
    private static final Logger LOGGER = Logger.getLogger(NbIcons.class.getName());

    @StaticResource
    public static final String PROJECT_ICON_PATH = "org/netbeans/gradle/project/resources/gradle.png";

    @StaticResource
    private static final String TASK_ICON_PATH = "org/netbeans/gradle/project/resources/task.gif";

    @StaticResource
    private static final String LIBRARIES_BADGE_ICON_PATH = "org/netbeans/gradle/project/resources/libraries-badge.png";

    @StaticResource
    private static final String LIBRARY_ICON_PATH = "org/netbeans/gradle/project/resources/libraries.png";

    @StaticResource
    private static final String WARNING_BADGE_ICON_PATH = "org/netbeans/gradle/project/resources/warning-badge.png";

    @StaticResource
    private static final String PRIORITY_HIGH_PATH = "org/netbeans/gradle/project/resources/priority_high.png";

    public static Image getGradleIcon() {
        return ImageUtilities.loadImage(PROJECT_ICON_PATH);
    }

    public static Icon getGradleIconAsIcon() {
        return ImageUtilities.loadImageIcon(PROJECT_ICON_PATH, true);
    }

    public static Image getTaskIcon() {
        return ImageUtilities.loadImage(TASK_ICON_PATH);
    }

    public static Image getLibraryIcon() {
        return ImageUtilities.loadImage(LIBRARY_ICON_PATH);
    }

    public static Image getLibrariesBadge() {
        return ImageUtilities.loadImage(LIBRARIES_BADGE_ICON_PATH);
    }

    public static Image getWarningBadge() {
        return ImageUtilities.loadImage(WARNING_BADGE_ICON_PATH);
    }

    public static Icon getPriorityHighIcon() {
        // This icon is the same as NotificationDisplayer.Priority.HIGH.getIcon()
        // in NB 7.4.
        // TODO: Remove this method and replace it with the above call.
        return ImageUtilities.loadImageIcon(PRIORITY_HIGH_PATH, false);
    }

    public static Icon getUIQuestionIcon() {
        return UIManager.getIcon("OptionPane.questionIcon");
    }

    public static Icon getUIWarningIcon() {
        return UIManager.getIcon("OptionPane.warningIcon");
    }

    public static Icon getUIErrorIcon() {
        return UIManager.getIcon("OptionPane.errorIcon");
    }

    private static class FolderIconHolder {
        public static final Image IMAGE = loadIcon();

        private static Image loadIcon() {
            Node n = DataFolder.findFolder(FileUtil.getConfigRoot()).getNodeDelegate();
            ImageIcon original = new ImageIcon(n.getIcon(BeanInfo.ICON_COLOR_16x16));
            Image result = original.getImage();
            if (result == null) {
                LOGGER.warning("Failed to load the folder icon.");
            }
            return result;
        }
    }

    public static Image getFolderIcon() {
        return FolderIconHolder.IMAGE;
    }

    private static class LibrariesIconHolder {
        public static final Image IMAGE = loadIcon();

        private static Image loadIcon() {
            Image folderIcon = getFolderIcon();
            Image badge = getLibrariesBadge();
            if (folderIcon != null && badge != null) {
                return ImageUtilities.mergeImages(folderIcon, badge, 7, 7);
            }
            else {
                LOGGER.warning("Failed to load the libraries icon.");
                return null;
            }
        }
    }

    public static Image getLibrariesIcon() {
        return LibrariesIconHolder.IMAGE;
    }

    private NbIcons() {
        throw new AssertionError();
    }
}
