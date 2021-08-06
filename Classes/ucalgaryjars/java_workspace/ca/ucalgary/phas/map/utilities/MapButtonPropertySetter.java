// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;

public final class MapButtonPropertySetter
{
    public static final void setCommonFeatures(final AbstractButton abstractButton) {
        abstractButton.setRolloverEnabled(true);
    }
    
    private static final void setLocaleStuff(final AbstractButton abstractButton) {
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            MapLocaleManager.addLocaleListener((MapLocaleListener)abstractButton);
            if (abstractButton instanceof MapButton) {
                ((MapButton)abstractButton).setLocaleDependantValues();
            }
            else if (abstractButton instanceof MapToggleButton) {
                ((MapToggleButton)abstractButton).setLocaleDependantValues();
            }
        }
    }
    
    public static final void setAboutIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/about.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/about_over.png", new MapButton())));
    }
    
    public static final void setAbout(final AbstractButton abstractButton) {
        setAboutIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("about");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setAbout() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setBackIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/back.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/back_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/back_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/back_over.png", new MapButton())));
    }
    
    public static final void setBack(final AbstractButton abstractButton) {
        setBackIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("back");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setBack() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setCompsIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/comps.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/comps_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/comps_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/comps_over.png", new MapButton())));
    }
    
    public static final void setComps(final AbstractButton abstractButton) {
        setCompsIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("comps");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setComps() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setFBDIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/fbd.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/fbd_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/fbd_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/fbd_over.png", new MapButton())));
    }
    
    public static final void setFBD(final AbstractButton abstractButton) {
        setFBDIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("fbd");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setFBD() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setForwardIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/forward.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/forward_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/forward_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/forward_over.png", new MapButton())));
    }
    
    public static final void setForward(final AbstractButton abstractButton) {
        setForwardIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("forward");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setForward() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setGridIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/grid.png", new MapButton())));
        commonFeatures.setSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/grid.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/grid_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/grid_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/grid_over.png", new MapButton())));
    }
    
    public static final void setGrid(final AbstractButton abstractButton) {
        setGridIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("grid");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setGrid() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setGraphIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/graph.png", new MapButton())));
        commonFeatures.setSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/graph.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/graph_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/graph_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/graph_over.png", new MapButton())));
    }
    
    public static final void setGraph(final AbstractButton abstractButton) {
        setGraphIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("graph");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setGraph() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setHelpIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/help.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/help_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/help_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/help_over.png", new MapButton())));
    }
    
    public static final void setHelp(final AbstractButton abstractButton) {
        setHelpIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("help");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setHelp() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setLeftPlayIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/leftplay.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/leftplay_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/leftplay_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/leftplay_over.png", new MapButton())));
    }
    
    public static final void setLeftPlay(final AbstractButton abstractButton) {
        setLeftPlayIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("leftplay");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setLeftPlay() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setIntegralIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/integral.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/integral_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/integral_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/integral_over.png", new MapButton())));
    }
    
    public static final void setIntegral(final AbstractButton abstractButton) {
        setIntegralIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("integral");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setIntegral() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setMapLogoIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        final ImageIcon pressedIcon = new ImageIcon(ImageUtils.getImageFromJar("images/maplogo.png", new MapButton()));
        pressedIcon.setImage(pressedIcon.getImage().getScaledInstance(16, 16, 4));
        commonFeatures.setIcon(pressedIcon);
        commonFeatures.setSelectedIcon(pressedIcon);
        commonFeatures.setRolloverIcon(pressedIcon);
        commonFeatures.setRolloverSelectedIcon(pressedIcon);
        commonFeatures.setPressedIcon(pressedIcon);
    }
    
    public static final void setPauseIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/pause.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/pause_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/pause_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/pause_over.png", new MapButton())));
    }
    
    public static final void setPause(final AbstractButton abstractButton) {
        setPauseIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("pause");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setPause() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setPlayIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/play.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/play_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/play_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/play_over.png", new MapButton())));
    }
    
    public static final void setPlay(final AbstractButton abstractButton) {
        setPlayIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("play");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setPlay() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setReplayIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/replay.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/replay_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/replay_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/replay_over.png", new MapButton())));
    }
    
    public static final void setReplay(final AbstractButton abstractButton) {
        setReplayIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("replay");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setReplay() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setResetIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/reset.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/reset_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/reset_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/reset_over.png", new MapButton())));
    }
    
    public static final void setReset(final AbstractButton abstractButton) {
        setResetIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("reset");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setReset() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setTraceIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/trace.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/trace_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/trace_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/trace_over.png", new MapButton())));
    }
    
    public static final void setTrace(final AbstractButton abstractButton) {
        setTraceIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("trace");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setTrace() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setVectorIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_over.png", new MapButton())));
    }
    
    public static final void setVector(final AbstractButton abstractButton) {
        setVectorIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("vector");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setVector() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setVectorPanelIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_over.png", new MapButton())));
    }
    
    public static final void setVectorPanel(final AbstractButton abstractButton) {
        setVectorPanelIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("vector_panel");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setVectorPanel() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setVectorVelocityIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_vel.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_vel_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_vel_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_vel_over.png", new MapButton())));
    }
    
    public static final void setVectorVelocity(final AbstractButton abstractButton) {
        setVectorVelocityIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("vector_velocity");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setVectorVelocity() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setVectorForceIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_force.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_force_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_force_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_force_over.png", new MapButton())));
    }
    
    public static final void setVectorForce(final AbstractButton abstractButton) {
        setVectorForceIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("vector_force");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setVectorForce() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setVectorMomentumIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_momentum.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_momentum_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_momentum_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_momentum_over.png", new MapButton())));
    }
    
    public static final void setVectorMomentum(final AbstractButton abstractButton) {
        setVectorMomentumIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("vector_momentum");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setVectorMomentum() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setVectorAccelerationIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_accel.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_accel_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_accel_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_accel_over.png", new MapButton())));
    }
    
    public static final void setVectorAcceleration(final AbstractButton abstractButton) {
        setVectorAccelerationIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("vector_acceleration");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setVectorAcceleration() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setVectorDisplacementIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_disp.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_disp_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_disp_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/vector_disp_over.png", new MapButton())));
    }
    
    public static final void setVectorDisplacement(final AbstractButton abstractButton) {
        setVectorDisplacementIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("vector_displacement");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setVectorDisplacement() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setLockIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/lock.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/lock_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/lock_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/lock_over.png", new MapButton())));
    }
    
    public static final void setLock(final AbstractButton abstractButton) {
        setLock(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("lock");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setLock() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setImageIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/image.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/image_over.png", new MapButton())));
    }
    
    public static final void setImage(final AbstractButton abstractButton) {
        setImageIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("image");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setImage() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setMoveIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/move.png", new MapButton())));
    }
    
    public static final void setMove(final AbstractButton abstractButton) {
        setMoveIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("move");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setMove() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setModeIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/mode.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/mode_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/mode_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/mode_over.png", new MapButton())));
    }
    
    public static final void setMode(final AbstractButton abstractButton) {
        setModeIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("mode");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setMode() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setLabelsIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/labels.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/labels_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/labels_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/labels_over.png", new MapButton())));
    }
    
    public static final void setLabels(final AbstractButton abstractButton) {
        setLabelsIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("labels");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setLabels() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setSweepsIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/sweeps.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/sweeps_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/sweeps_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/sweeps_over.png", new MapButton())));
    }
    
    public static final void setSweeps(final AbstractButton abstractButton) {
        setSweepsIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("sweeps");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setSweeps() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setNavIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/nav.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/nav_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/nav_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/nav_over.png", new MapButton())));
    }
    
    public static final void setNav(final AbstractButton abstractButton) {
        setNavIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("nav");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setNav() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setPosNegAngleIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/posnegangle.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/posnegangle_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/posnegangle_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/posnegangle_over.png", new MapButton())));
    }
    
    public static final void setPosNegAngle(final AbstractButton abstractButton) {
        setPosNegAngleIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("posnegangle");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setPosNegAngle() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setPosAngleIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/posangle.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/posangle_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/posangle_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/posangle_over.png", new MapButton())));
    }
    
    public static final void setPosAngle(final AbstractButton abstractButton) {
        setPosAngleIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("posangle");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setPosAngle() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setCartesianIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/cartesian.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/cartesian_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/cartesian_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/cartesian_over.png", new MapButton())));
    }
    
    public static final void setCartesian(final AbstractButton abstractButton) {
        setCartesianIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("posangle");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setCartesian() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setCoordinateIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/coordinate.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/coordinate_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/coordinate_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/coordinate_over.png", new MapButton())));
    }
    
    public static final void setCoordinate(final AbstractButton abstractButton) {
        setCoordinateIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("coordinate");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setCoordinate() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setExtrapolateIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/extrapolate.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/extrapolate_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/extrapolate_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/extrapolate_over.png", new MapButton())));
    }
    
    public static final void setExtrapolate(final AbstractButton abstractButton) {
        setExtrapolateIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("extrapolate");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setExtrapolate() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setDeleteIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/eraser.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/eraser_over.png", new MapButton())));
    }
    
    public static final void setDelete(final AbstractButton abstractButton) {
        setDeleteIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("delete");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setDelete() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setDataIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/data.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/data_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/data_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/data_over.png", new MapButton())));
    }
    
    public static final void setData(final AbstractButton abstractButton) {
        setDataIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("data");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setData() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setTargetIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/target.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/target_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/target_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/target_over.png", new MapButton())));
    }
    
    public static final void setTarget(final AbstractButton abstractButton) {
        setTargetIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("target");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setTarget() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setResultantVectorIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/resultantVector.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/resultantVector_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/resultantVector_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/resultantVector_over.png", new MapButton())));
    }
    
    public static final void setResultantVector(final AbstractButton abstractButton) {
        setResultantVectorIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("resultantVector");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setResultantVector() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setSightIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/sight.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/sight_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/sight_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/sight_over.png", new MapButton())));
    }
    
    public static final void setSight(final AbstractButton abstractButton) {
        setSightIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("sight");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setSight() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setZoomIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/zoom.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/zoom_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/zoom_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/zoom_over.png", new MapButton())));
    }
    
    public static final void setZoom(final AbstractButton abstractButton) {
        setZoomIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("zoom");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setZoom() must be passed a MapButton, or a MapToggleButton");
        }
    }
    
    public static final void setAxisIcon(final AbstractButton commonFeatures) {
        setCommonFeatures(commonFeatures);
        commonFeatures.setIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/axis.png", new MapButton())));
        commonFeatures.setRolloverIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/axis_over.png", new MapButton())));
        commonFeatures.setRolloverSelectedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/axis_over.png", new MapButton())));
        commonFeatures.setPressedIcon(new ImageIcon(ImageUtils.getImageFromJar("buttonimages/axis_over.png", new MapButton())));
    }
    
    public static final void setAxis(final AbstractButton abstractButton) {
        setAxisIcon(abstractButton);
        if (abstractButton instanceof MapButton || abstractButton instanceof MapToggleButton) {
            abstractButton.setName("axis");
            setLocaleStuff(abstractButton);
        }
        else {
            System.out.println("MapButtonPropertySetter.setAxis() must be passed a MapButton, or a MapToggleButton");
        }
    }
}
