package com.structure;

import com.player.Player;
import com.utils.Stats;

public interface Position{
	public abstract boolean play(Player player, Stats[] s);
}