/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Copyright 2009, 2010 Caprica Software Limited.
 */

package uk.co.caprica.vlcj.player.embedded.linux;

import java.awt.Canvas;

import org.apache.log4j.Logger;

import uk.co.caprica.vlcj.binding.internal.libvlc_instance_t;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_player_t;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;

import com.sun.jna.Native;

/**
 *
 */
public class LinuxEmbeddedMediaPlayer extends EmbeddedMediaPlayer {

  /**
   * Log.
   */
  private static final Logger LOG = Logger.getLogger(LinuxEmbeddedMediaPlayer.class);
  
  /**
   * 
   * 
   * @param instance
   * @param fullScreenStrategy
   */
  public LinuxEmbeddedMediaPlayer(libvlc_instance_t instance, FullScreenStrategy fullScreenStrategy) {
    super(instance, fullScreenStrategy);
  }

  @Override
  protected void nativeSetVideoSurface(libvlc_media_player_t mediaPlayerInstance, Canvas videoSurface) {
    if(LOG.isDebugEnabled()) {LOG.debug("nativeSetVideoSurface(mediaPlayerInstance=" + mediaPlayerInstance + ",videoSurface=" + videoSurface + ")");}
    
    // The video surface component must be realised (visible and laid out etc)
    // at this point
    long drawable = Native.getComponentID(videoSurface);
    if(LOG.isDebugEnabled()) {LOG.debug("drawable=" + drawable);}
    
    libvlc.libvlc_media_player_set_xwindow(mediaPlayerInstance, (int)drawable);
  }
}
