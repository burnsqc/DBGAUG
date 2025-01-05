package com.dbgaug.client.gui.components;

import java.util.List;

import javax.annotation.Nullable;

import com.dbgaug.util.math.PosDiffs;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.LevelChunk;

public class WorldgenScreenOverlay {
	private final Minecraft minecraft;
	private final Font font;
	public static boolean shouldRender;
	@Nullable
	private LevelChunk clientChunk;
	@Nullable
	private ChunkPos lastPos;

	public WorldgenScreenOverlay(Minecraft minecraft) {
		this.minecraft = minecraft;
		this.font = minecraft.font;
	}

	@SuppressWarnings("deprecation")
	public void render(GuiGraphics guiGraphics) {
		if (shouldRender) {
			this.minecraft.getProfiler().push("debug_worldgen");
			guiGraphics.pose().pushPose();
			guiGraphics.pose().scale(0.8f, 0.8f, 0.8f);
			guiGraphics.drawManaged(() -> {
				this.drawPositionInformation(guiGraphics);
			});
			guiGraphics.pose().popPose();
			this.minecraft.getProfiler().pop();
		}
	}

	private void drawPositionInformation(GuiGraphics guiGraphics) {
		String[][] fields = this.getPositionInfo();
		this.renderPositionInfo(guiGraphics, fields);
	}

	protected List<String> getPositionInformation() {
		List<String> list = Lists.newArrayList("");
		return list;
	}

	protected String[][] getPositionInfo() {
		Entity entity = minecraft.getCameraEntity();
		BlockPos blockPos = entity.blockPosition();
		SectionPos sectionPos = SectionPos.of(blockPos);
		ChunkPos chunkPos = new ChunkPos(blockPos);

		// Relative to level. Start small, go big.
		double playerRelativeToLevelX = entity.getX(); // Good
		double playerRelativeToLevelY = entity.getY(); // Good
		double playerRelativeToLevelZ = entity.getZ(); // Good

		int blockRelativeToLevelX = blockPos.getX(); // Good
		int blockRelativeToLevelY = blockPos.getY(); // Good
		int blockRelativeToLevelZ = blockPos.getZ(); // Good

		int sectionRelativeToLevelX = chunkPos.x;
		int sectionRelativeToLevelY = SectionPos.blockToSectionCoord(blockRelativeToLevelY);
		int sectionRelativeToLevelZ = chunkPos.z;

		int chunkRelativeToLevelX = sectionRelativeToLevelX;
		int chunkRelativeToLevelY = 0;
		int chunkRelativeToLevelZ = sectionRelativeToLevelZ;

		int regionRelativeToLevelX = chunkPos.getRegionX();
		int regionRelativeToLevelY = 0;
		int regionRelativeToLevelZ = chunkPos.getRegionZ();

		int chunkRelativeToRegionX = chunkPos.getRegionLocalX();
		int chunkRelativeToRegionY = 0;
		int chunkRelativeToRegionZ = chunkPos.getRegionLocalZ();

		// Relative to chunk. Start small, go big.
		double playerRelativeToBlockX = playerRelativeToLevelX - blockRelativeToLevelX;
		double playerRelativeToBlockY = playerRelativeToLevelY - blockRelativeToLevelY;
		double playerRelativeToBlockZ = playerRelativeToLevelZ - blockRelativeToLevelZ;

		double playerRelativeToRegionX = (blockRelativeToLevelX & 15) + playerRelativeToBlockX;
		double playerRelativeToRegionY = (blockRelativeToLevelY & 15) + playerRelativeToBlockY;
		double playerRelativeToRegionZ = (blockRelativeToLevelZ & 15) + playerRelativeToBlockZ;

		double playerRelativeToChunkX = playerRelativeToRegionX;
		double playerRelativeToChunkY = playerRelativeToLevelY;
		double playerRelativeToChunkZ = playerRelativeToRegionZ;

		int blockRelativeToChunkX = blockPos.getX() & 15;
		int blockRelativeToChunkY = blockPos.getY();
		int blockRelativeToChunkZ = blockPos.getZ() & 15;

		int sectionRelativeToChunkX = 0;
		int sectionRelativeToChunkY = regionRelativeToLevelY;
		int sectionRelativeToChunkZ = 0;

		// Relative to region. Start small, go big.

		int blockRelativeToRegionX = blockPos.getX() & 15;
		int blockRelativeToRegionY = blockPos.getY() & 15;
		int blockRelativeToRegionZ = blockPos.getZ() & 15;

		String[][] fields = new String[27][13];

		fields[0][0] = "DbAux - Position";
		fields[1][0] = "";
		fields[2][0] = "Current";
		fields[3][0] = "Entity";
		fields[4][0] = "Relative to Level";
		fields[5][0] = "Relative to Region";
		fields[6][0] = "Relative to Chunk";
		fields[7][0] = "Relative to Section";
		fields[8][0] = "Relative to Block";
		fields[9][0] = "";
		fields[10][0] = "Block";
		fields[11][0] = "Relative to Level";
		fields[12][0] = "Relative to Region";
		fields[13][0] = "Relative to Chunk";
		fields[14][0] = "Relative to Section";
		fields[15][0] = "";
		fields[16][0] = "Section";
		fields[17][0] = "Relative to Level";
		fields[18][0] = "Relative to Region";
		fields[19][0] = "Relative to Chunk";
		fields[20][0] = "";
		fields[21][0] = "Chunk";
		fields[22][0] = "Relative to Level";
		fields[23][0] = "Relative to Region";
		fields[24][0] = "";
		fields[25][0] = "Region";
		fields[26][0] = "Relative to Level";

		fields[0][1] = "";
		fields[1][1] = "";
		fields[2][1] = "";
		fields[3][1] = "X";
		fields[4][1] = String.format("%.3f", playerRelativeToLevelX);
		fields[5][1] = String.format("%d", blockRelativeToLevelX);
		fields[6][1] = String.format("%d", sectionRelativeToLevelX);
		fields[7][1] = String.format("%d", chunkRelativeToLevelX);
		fields[7][1] = "";
		fields[8][1] = "";
		fields[9][1] = "";
		fields[10][1] = "";
		fields[11][1] = String.format("%d", blockRelativeToLevelX);
		fields[12][1] = "";
		fields[13][1] = "";
		fields[14][1] = "";
		fields[15][1] = "";
		fields[16][1] = "";
		fields[17][1] = String.format("%d", sectionRelativeToLevelX);
		fields[18][1] = "";
		fields[19][1] = "";
		fields[20][1] = "";
		fields[21][1] = "";
		fields[22][1] = String.format("%d", PosDiffs.chunkRelativeToLevel(blockPos).getX());
		fields[23][1] = String.format("%d", chunkRelativeToRegionX);
		fields[24][1] = "";
		fields[25][1] = "";
		fields[26][1] = String.format("%d", regionRelativeToLevelX);

		fields[0][2] = "";
		fields[1][2] = "";
		fields[2][2] = "";
		fields[3][2] = "Y";
		fields[4][2] = String.format("%.3f", playerRelativeToLevelY);
		fields[5][2] = String.format("%d", blockRelativeToLevelY);
		fields[6][2] = String.format("%d", regionRelativeToLevelY);
		fields[7][2] = String.format("%d", chunkRelativeToLevelY);
		fields[8][2] = "";
		fields[9][2] = "";
		fields[10][2] = "";
		fields[11][2] = String.format("%d", blockRelativeToLevelY);
		fields[12][2] = "";
		fields[13][2] = "";
		fields[14][2] = "";
		fields[15][2] = "";
		fields[16][2] = "";
		fields[17][2] = String.format("%d", sectionRelativeToLevelY);
		fields[18][2] = "";
		fields[19][2] = "";
		fields[20][2] = "";
		fields[21][2] = "";
		fields[22][2] = String.format("%d", chunkRelativeToLevelY);
		fields[23][2] = String.format("%d", chunkRelativeToRegionY);
		fields[24][2] = "";
		fields[25][2] = "";
		fields[26][2] = String.format("%d", regionRelativeToLevelY);

		fields[0][3] = "";
		fields[1][3] = "";
		fields[2][3] = "";
		fields[3][3] = "Z";
		fields[4][3] = String.format("%.3f", playerRelativeToLevelZ);
		fields[5][3] = String.format("%d", blockRelativeToLevelZ);
		fields[6][3] = String.format("%d", sectionRelativeToLevelZ);
		fields[7][3] = String.format("%d", chunkRelativeToLevelZ);
		fields[8][3] = "";
		fields[9][3] = "";
		fields[10][3] = "";
		fields[11][3] = String.format("%d", blockRelativeToLevelZ);
		fields[12][3] = "";
		fields[13][3] = "";
		fields[14][3] = "";
		fields[15][3] = "";
		fields[16][3] = "";
		fields[17][3] = String.format("%d", sectionRelativeToLevelZ);
		fields[18][3] = "";
		fields[19][3] = "";
		fields[20][3] = "";
		fields[21][3] = "";
		fields[22][3] = String.format("%d", chunkRelativeToLevelZ);
		fields[23][3] = String.format("%d", chunkRelativeToRegionZ);
		fields[24][3] = "";
		fields[25][3] = "";
		fields[26][3] = String.format("%d", regionRelativeToLevelZ);

		return fields;
	}

	private void renderPositionInfo(GuiGraphics pGuiGraphics, String[][] fields) {
		boolean lightOrDark = false;

		for (int row = 0; row < fields.length; ++row) {
			lightOrDark = !lightOrDark;
			int fill = lightOrDark ? -1873784752 : -1875758542;
			int verticalStart = 2 + 9 * row;
			pGuiGraphics.fill(1, verticalStart - 1, 500, verticalStart + 8, fill);
		}

		for (int row = 0; row < fields.length; ++row) {
			int verticalStart = 2 + 9 * row;

			for (int column = 0; column < fields[row].length; ++column) {
				String field = fields[row][column];
				if (!Strings.isNullOrEmpty(field)) {
					int fieldLength = this.font.width(Component.literal(field));
					int horizontalStart = column == 0 ? 2 : 70 + column * 50 - fieldLength / 2;
					pGuiGraphics.drawString(font, Component.literal(field).withStyle(ChatFormatting.GREEN), horizontalStart, verticalStart, 14737632, false);
				}
			}
		}
	}
}
