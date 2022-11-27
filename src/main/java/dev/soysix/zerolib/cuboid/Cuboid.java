package dev.soysix.zerolib.cuboid;

import dev.soysix.zerolib.ZeroLib;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class Cuboid implements Iterable<Block>, Cloneable, ConfigurationSerializable {
   protected final String worldName;
   protected final int x1;
   protected final int y1;
   protected final int z1;
   protected final int x2;
   protected final int y2;
   protected final int z2;

   public Cuboid(Location locationOne, Location locationTwo) {
      if (!locationOne.getWorld().equals(locationTwo.getWorld())) {
         throw new IllegalArgumentException("Locations must be on the same world");
      } else {
         this.worldName = locationOne.getWorld().getName();
         this.x1 = Math.min(locationOne.getBlockX(), locationTwo.getBlockX());
         this.y1 = Math.min(locationOne.getBlockY(), locationTwo.getBlockY());
         this.z1 = Math.min(locationOne.getBlockZ(), locationTwo.getBlockZ());
         this.x2 = Math.max(locationOne.getBlockX(), locationTwo.getBlockX());
         this.y2 = Math.max(locationOne.getBlockY(), locationTwo.getBlockY());
         this.z2 = Math.max(locationOne.getBlockZ(), locationTwo.getBlockZ());
      }
   }

   public Cuboid(Location l1) {
      this(l1, l1);
   }

   public Cuboid(Cuboid other) {
      this(other.getWorld().getName(), other.x1, other.y1, other.z1, other.x2, other.y2, other.z2);
   }

   public Cuboid(World world, int x1, int y1, int z1, int x2, int y2, int z2) {
      this.worldName = world.getName();
      this.x1 = Math.min(x1, x2);
      this.x2 = Math.max(x1, x2);
      this.y1 = Math.min(y1, y2);
      this.y2 = Math.max(y1, y2);
      this.z1 = Math.min(z1, z2);
      this.z2 = Math.max(z1, z2);
   }

   private Cuboid(String worldName, int x1, int y1, int z1, int x2, int y2, int z2) {
      this.worldName = worldName;
      this.x1 = Math.min(x1, x2);
      this.x2 = Math.max(x1, x2);
      this.y1 = Math.min(y1, y2);
      this.y2 = Math.max(y1, y2);
      this.z1 = Math.min(z1, z2);
      this.z2 = Math.max(z1, z2);
   }

   public Cuboid(Map<String, Object> map) {
      this.worldName = (String)map.get("worldName");
      this.x1 = (Integer)map.get("x1");
      this.x2 = (Integer)map.get("x2");
      this.y1 = (Integer)map.get("y1");
      this.y2 = (Integer)map.get("y2");
      this.z1 = (Integer)map.get("z1");
      this.z2 = (Integer)map.get("z2");
   }

   public Map<String, Object> serialize() {
      Map<String, Object> map = new HashMap();
      map.put("worldName", this.worldName);
      map.put("x1", this.x1);
      map.put("y1", this.y1);
      map.put("z1", this.z1);
      map.put("x2", this.x2);
      map.put("y2", this.y2);
      map.put("z2", this.z2);
      return map;
   }

   public Location getLowerNE() {
      return new Location(this.getWorld(), (double)this.x1, (double)this.y1, (double)this.z1);
   }

   public Location getUpperSW() {
      return new Location(this.getWorld(), (double)this.x2, (double)this.y2, (double)this.z2);
   }

   public List<Block> getBlocks() {
      Iterator<Block> blockI = this.iterator();
      ArrayList copy = new ArrayList();

      while(blockI.hasNext()) {
         copy.add(blockI.next());
      }

      return copy;
   }

   public Location getCenter() {
      int x1 = this.getUpperX() + 1;
      int y1 = this.getUpperY() + 1;
      int z1 = this.getUpperZ() + 1;
      return new Location(this.getWorld(), (double)this.getLowerX() + (double)(x1 - this.getLowerX()) / 2.0D, (double)this.getLowerY() + (double)(y1 - this.getLowerY()) / 2.0D, (double)this.getLowerZ() + (double)(z1 - this.getLowerZ()) / 2.0D);
   }

   public World getWorld() {
      World world = ZeroLib.INSTANCE.getPlugin().getServer().getWorld(this.worldName);
      if (world == null) {
         throw new IllegalStateException("World '" + this.worldName + "' is not loaded");
      } else {
         return world;
      }
   }

   public int getSizeX() {
      return this.x2 - this.x1 + 1;
   }

   public int getSizeY() {
      return this.y2 - this.y1 + 1;
   }

   public int getSizeZ() {
      return this.z2 - this.z1 + 1;
   }

   public int getLowerX() {
      return this.x1;
   }

   public int getLowerY() {
      return this.y1;
   }

   public int getLowerZ() {
      return this.z1;
   }

   public int getUpperX() {
      return this.x2;
   }

   public int getUpperY() {
      return this.y2;
   }

   public int getUpperZ() {
      return this.z2;
   }

   public Block[] corners() {
      Block[] res = new Block[8];
      World w = this.getWorld();
      res[0] = w.getBlockAt(this.x1, this.y1, this.z1);
      res[1] = w.getBlockAt(this.x1, this.y1, this.z2);
      res[2] = w.getBlockAt(this.x1, this.y2, this.z1);
      res[3] = w.getBlockAt(this.x1, this.y2, this.z2);
      res[4] = w.getBlockAt(this.x2, this.y1, this.z1);
      res[5] = w.getBlockAt(this.x2, this.y1, this.z2);
      res[6] = w.getBlockAt(this.x2, this.y2, this.z1);
      res[7] = w.getBlockAt(this.x2, this.y2, this.z2);
      return res;
   }

   public Block[] minCorners() {
      Block[] res = new Block[4];
      World w = this.getWorld();
      res[0] = w.getBlockAt(this.x1, this.y1, this.z1);
      return res;
   }

   public Cuboid expand(Cuboid.CuboidDirection dir, int amount) {
      switch(dir) {
      case NORTH:
         return new Cuboid(this.worldName, this.x1 - amount, this.y1, this.z1, this.x2, this.y2, this.z2);
      case SOUTH:
         return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2 + amount, this.y2, this.z2);
      case EAST:
         return new Cuboid(this.worldName, this.x1, this.y1, this.z1 - amount, this.x2, this.y2, this.z2);
      case WEST:
         return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, this.z2 + amount);
      case DOWN:
         return new Cuboid(this.worldName, this.x1, this.y1 - amount, this.z1, this.x2, this.y2, this.z2);
      case UP:
         return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2 + amount, this.z2);
      default:
         throw new IllegalArgumentException("Invalid direction " + dir);
      }
   }

   public Cuboid shift(Cuboid.CuboidDirection dir, int amount) {
      return this.expand(dir, amount).expand(dir.opposite(), -amount);
   }

   public Cuboid outset(Cuboid.CuboidDirection dir, int amount) {
      Cuboid c;
      switch(dir) {
      case HORIZONTAL:
         c = this.expand(Cuboid.CuboidDirection.NORTH, amount).expand(Cuboid.CuboidDirection.SOUTH, amount).expand(Cuboid.CuboidDirection.EAST, amount).expand(Cuboid.CuboidDirection.WEST, amount);
         return c;
      case VERTICAL:
         c = this.expand(Cuboid.CuboidDirection.DOWN, amount).expand(Cuboid.CuboidDirection.UP, amount);
         return c;
      case BOTH:
         c = this.outset(Cuboid.CuboidDirection.HORIZONTAL, amount).outset(Cuboid.CuboidDirection.VERTICAL, amount);
         return c;
      default:
         throw new IllegalArgumentException("Invalid direction " + dir);
      }
   }

   public Cuboid inset(Cuboid.CuboidDirection dir, int amount) {
      return this.outset(dir, -amount);
   }

   public boolean contains(int x, int y, int z) {
      return x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2 && z >= this.z1 && z <= this.z2;
   }

   public boolean contains(Block b) {
      return this.contains(b.getLocation());
   }

   public boolean contains(Location l) {
      return !this.worldName.equals(l.getWorld().getName()) ? false : this.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ());
   }

   public boolean contains(Entity e) {
      return this.contains(e.getLocation());
   }

   public Cuboid grow(int i) {
      return this.expand(Cuboid.CuboidDirection.NORTH, i).expand(Cuboid.CuboidDirection.SOUTH, i).expand(Cuboid.CuboidDirection.EAST, i).expand(Cuboid.CuboidDirection.WEST, i);
   }

   public int getVolume() {
      return this.getSizeX() * this.getSizeY() * this.getSizeZ();
   }

   public byte getAverageLightLevel() {
      long total = 0L;
      int n = 0;
      Iterator var4 = this.iterator();

      while(var4.hasNext()) {
         Block b = (Block)var4.next();
         if (b.isEmpty()) {
            total += (long)b.getLightLevel();
            ++n;
         }
      }

      return n > 0 ? (byte)((int)(total / (long)n)) : 0;
   }

   public Cuboid contract() {
      return this.contract(Cuboid.CuboidDirection.DOWN).contract(Cuboid.CuboidDirection.SOUTH).contract(Cuboid.CuboidDirection.EAST).contract(Cuboid.CuboidDirection.UP).contract(Cuboid.CuboidDirection.NORTH).contract(Cuboid.CuboidDirection.WEST);
   }

   public Cuboid contract(Cuboid.CuboidDirection dir) {
      Cuboid face = this.getFace(dir.opposite());
      switch(dir) {
      case NORTH:
         while(face.containsOnly(0) && face.getLowerX() > this.getLowerX()) {
            face = face.shift(Cuboid.CuboidDirection.NORTH, 1);
         }

         return new Cuboid(this.worldName, this.x1, this.y1, this.z1, face.getUpperX(), this.y2, this.z2);
      case SOUTH:
         while(face.containsOnly(0) && face.getUpperX() < this.getUpperX()) {
            face = face.shift(Cuboid.CuboidDirection.SOUTH, 1);
         }

         return new Cuboid(this.worldName, face.getLowerX(), this.y1, this.z1, this.x2, this.y2, this.z2);
      case EAST:
         while(face.containsOnly(0) && face.getLowerZ() > this.getLowerZ()) {
            face = face.shift(Cuboid.CuboidDirection.EAST, 1);
         }

         return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, face.getUpperZ());
      case WEST:
         while(face.containsOnly(0) && face.getUpperZ() < this.getUpperZ()) {
            face = face.shift(Cuboid.CuboidDirection.WEST, 1);
         }

         return new Cuboid(this.worldName, this.x1, this.y1, face.getLowerZ(), this.x2, this.y2, this.z2);
      case DOWN:
         while(face.containsOnly(0) && face.getLowerY() > this.getLowerY()) {
            face = face.shift(Cuboid.CuboidDirection.DOWN, 1);
         }

         return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, face.getUpperY(), this.z2);
      case UP:
         while(face.containsOnly(0) && face.getUpperY() < this.getUpperY()) {
            face = face.shift(Cuboid.CuboidDirection.UP, 1);
         }

         return new Cuboid(this.worldName, this.x1, face.getLowerY(), this.z1, this.x2, this.y2, this.z2);
      default:
         throw new IllegalArgumentException("Invalid direction " + dir);
      }
   }

   public Cuboid getFace(Cuboid.CuboidDirection dir) {
      switch(dir) {
      case NORTH:
         return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x1, this.y2, this.z2);
      case SOUTH:
         return new Cuboid(this.worldName, this.x2, this.y1, this.z1, this.x2, this.y2, this.z2);
      case EAST:
         return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y2, this.z1);
      case WEST:
         return new Cuboid(this.worldName, this.x1, this.y1, this.z2, this.x2, this.y2, this.z2);
      case DOWN:
         return new Cuboid(this.worldName, this.x1, this.y1, this.z1, this.x2, this.y1, this.z2);
      case UP:
         return new Cuboid(this.worldName, this.x1, this.y2, this.z1, this.x2, this.y2, this.z2);
      default:
         throw new IllegalArgumentException("Invalid direction " + dir);
      }
   }

   public boolean containsOnly(int blockId) {
      Iterator var2 = this.iterator();

      Block b;
      do {
         if (!var2.hasNext()) {
            return true;
         }

         b = (Block)var2.next();
      } while(b.getTypeId() == blockId);

      return false;
   }

   public Cuboid getBoundingCuboid(Cuboid other) {
      if (other == null) {
         return this;
      } else {
         int xMin = Math.min(this.getLowerX(), other.getLowerX());
         int yMin = Math.min(this.getLowerY(), other.getLowerY());
         int zMin = Math.min(this.getLowerZ(), other.getLowerZ());
         int xMax = Math.max(this.getUpperX(), other.getUpperX());
         int yMax = Math.max(this.getUpperY(), other.getUpperY());
         int zMax = Math.max(this.getUpperZ(), other.getUpperZ());
         return new Cuboid(this.worldName, xMin, yMin, zMin, xMax, yMax, zMax);
      }
   }

   public Block getRelativeBlock(int x, int y, int z) {
      return this.getWorld().getBlockAt(this.x1 + x, this.y1 + y, this.z1 + z);
   }

   public Block getRelativeBlock(World w, int x, int y, int z) {
      return w.getBlockAt(this.x1 + x, this.y1 + y, this.z1 + z);
   }

   public List<Chunk> getChunks() {
      List<Chunk> res = new ArrayList();
      World w = this.getWorld();
      int x1 = this.getLowerX() & -16;
      int x2 = this.getUpperX() & -16;
      int z1 = this.getLowerZ() & -16;
      int z2 = this.getUpperZ() & -16;

      for(int x = x1; x <= x2; x += 16) {
         for(int z = z1; z <= z2; z += 16) {
            res.add(w.getChunkAt(x >> 4, z >> 4));
         }
      }

      return res;
   }

   public Iterator<Block> iterator() {
      return new Cuboid.CuboidIterator(this.getWorld(), this.x1, this.y1, this.z1, this.x2, this.y2, this.z2);
   }

   public Iterator<Location> locationIterator() {
      return new Cuboid.CuboidLocationIterator(this.getWorld(), this.x1, this.y1, this.z1, this.x2, this.y2, this.z2);
   }

   public List<Vector> edges() {
      return this.edges(-1, -1, -1, -1);
   }

   public Location getMinimumPoint() {
      return new Location(this.getWorld(), (double)Math.min(this.x1, this.x2), (double)Math.min(this.y1, this.y2), (double)Math.min(this.z1, this.z2));
   }

   public Location getMaximumPoint() {
      return new Location(this.getWorld(), (double)Math.max(this.x1, this.x2), (double)Math.max(this.y1, this.y2), (double)Math.max(this.z1, this.z2));
   }

   public List<Vector> edges(int fixedMinX, int fixedMaxX, int fixedMinZ, int fixedMaxZ) {
      Vector v1 = this.getMinimumPoint().toVector();
      Vector v2 = this.getMaximumPoint().toVector();
      int minX = v1.getBlockX();
      int maxX = v2.getBlockX();
      int minZ = v1.getBlockZ();
      int maxZ = v2.getBlockZ();
      int capacity = (maxX - minX) * 4 + (maxZ - minZ) * 4;
      capacity += 4;
      ArrayList<Vector> result = new ArrayList(capacity);
      if (capacity <= 0) {
         return result;
      } else {
         int minY = v1.getBlockY();
         int maxY = v1.getBlockY();

         int z;
         for(z = minX; z <= maxX; ++z) {
            result.add(new Vector(z, minY, minZ));
            result.add(new Vector(z, minY, maxZ));
            result.add(new Vector(z, maxY, minZ));
            result.add(new Vector(z, maxY, maxZ));
         }

         for(z = minZ; z <= maxZ; ++z) {
            result.add(new Vector(minX, minY, z));
            result.add(new Vector(minX, maxY, z));
            result.add(new Vector(maxX, minY, z));
            result.add(new Vector(maxX, maxY, z));
         }

         return result;
      }
   }

   public Cuboid clone() {
      return new Cuboid(this);
   }

   public String toString() {
      return new String("Cuboid: " + this.worldName + "," + this.x1 + "," + this.y1 + "," + this.z1 + "=>" + this.x2 + "," + this.y2 + "," + this.z2);
   }

   public List<Block> getWalls() {
      List<Block> blocks = new ArrayList();
      Location min = new Location(this.getWorld(), (double)this.x1, (double)this.y1, (double)this.z1);
      Location max = new Location(this.getWorld(), (double)this.x2, (double)this.y2, (double)this.z2);
      int minX = min.getBlockX();
      int minY = min.getBlockY();
      int minZ = min.getBlockZ();
      int maxX = max.getBlockX();
      int maxY = max.getBlockY();
      int maxZ = max.getBlockZ();

      int y;
      int z;
      Location minLoc;
      Location maxLoc;
      for(y = minX; y <= maxX; ++y) {
         for(z = minY; z <= maxY; ++z) {
            minLoc = new Location(this.getWorld(), (double)y, (double)z, (double)minZ);
            maxLoc = new Location(this.getWorld(), (double)y, (double)z, (double)maxZ);
            blocks.add(minLoc.getBlock());
            blocks.add(maxLoc.getBlock());
         }
      }

      for(y = minY; y <= maxY; ++y) {
         for(z = minZ; z <= maxZ; ++z) {
            minLoc = new Location(this.getWorld(), (double)minX, (double)y, (double)z);
            maxLoc = new Location(this.getWorld(), (double)maxX, (double)y, (double)z);
            blocks.add(minLoc.getBlock());
            blocks.add(maxLoc.getBlock());
         }
      }

      return blocks;
   }

   public List<Block> getFaces() {
      List<Block> blocks = new ArrayList();
      Location min = new Location(this.getWorld(), (double)this.x1, (double)this.y1, (double)this.z1);
      Location max = new Location(this.getWorld(), (double)this.x2, (double)this.y2, (double)this.z2);
      int minX = min.getBlockX();
      int minY = min.getBlockY();
      int minZ = min.getBlockZ();
      int maxX = max.getBlockX();
      int maxY = max.getBlockY();
      int maxZ = max.getBlockZ();

      int z;
      int i;
      for(z = minX; z <= maxX; ++z) {
         for(i = minY; i <= maxY; ++i) {
            blocks.add((new Location(this.getWorld(), (double)z, (double)i, (double)minZ)).getBlock());
            blocks.add((new Location(this.getWorld(), (double)z, (double)i, (double)maxZ)).getBlock());
         }
      }

      for(z = minY; z <= maxY; ++z) {
         for(i = minZ; i <= maxZ; ++i) {
            blocks.add((new Location(this.getWorld(), (double)minX, (double)z, (double)i)).getBlock());
            blocks.add((new Location(this.getWorld(), (double)maxX, (double)z, (double)i)).getBlock());
         }
      }

      for(z = minZ; z <= maxZ; ++z) {
         for(i = minX; i <= maxX; ++i) {
            blocks.add((new Location(this.getWorld(), (double)i, (double)minY, (double)z)).getBlock());
            blocks.add((new Location(this.getWorld(), (double)i, (double)maxY, (double)z)).getBlock());
         }
      }

      return blocks;
   }

   public class CuboidLocationIterator implements Iterator<Location> {
      private final World world;
      private final int baseX;
      private final int baseY;
      private final int baseZ;
      private final int sizeX;
      private final int sizeY;
      private final int sizeZ;
      private int x;
      private int y;
      private int z;

      public CuboidLocationIterator(World world, int x1, int y1, int z1, int x2, int y2, int z2) {
         this.world = world;
         this.baseX = x1;
         this.baseY = y1;
         this.baseZ = z1;
         this.sizeX = Math.abs(x2 - x1) + 1;
         this.sizeY = Math.abs(y2 - y1) + 1;
         this.sizeZ = Math.abs(z2 - z1) + 1;
         this.z = 0;
         this.y = 0;
         this.x = 0;
      }

      public boolean hasNext() {
         return this.x < this.sizeX && this.y < this.sizeY && this.z < this.sizeZ;
      }

      public Location next() {
         Location location = new Location(this.world, (double)(this.baseX + this.x), (double)(this.baseY + this.y), (double)(this.baseZ + this.z));
         if (++this.x >= this.sizeX) {
            this.x = 0;
            if (++this.y >= this.sizeY) {
               this.y = 0;
               ++this.z;
            }
         }

         return location;
      }

      public void remove() throws UnsupportedOperationException {
         throw new UnsupportedOperationException();
      }
   }

   public class CuboidIterator implements Iterator<Block> {
      private World w;
      private int baseX;
      private int baseY;
      private int baseZ;
      private int x;
      private int y;
      private int z;
      private int sizeX;
      private int sizeY;
      private int sizeZ;

      public CuboidIterator(World w, int x1, int y1, int z1, int x2, int y2, int z2) {
         this.w = w;
         this.baseX = Math.min(x1, x2);
         this.baseY = Math.min(y1, y2);
         this.baseZ = Math.min(z1, z2);
         this.sizeX = Math.abs(x2 - x1) + 1;
         this.sizeY = Math.abs(y2 - y1) + 1;
         this.sizeZ = Math.abs(z2 - z1) + 1;
         this.x = this.y = this.z = 0;
      }

      public boolean hasNext() {
         return this.x < this.sizeX && this.y < this.sizeY && this.z < this.sizeZ;
      }

      public Block next() {
         Block b = this.w.getBlockAt(this.baseX + this.x, this.baseY + this.y, this.baseZ + this.z);
         this.x = 0;
         if (++this.x >= this.sizeX && ++this.y >= this.sizeY) {
            this.y = 0;
            ++this.z;
         }

         return b;
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   public static enum CuboidDirection {
      NORTH,
      EAST,
      SOUTH,
      WEST,
      UP,
      DOWN,
      HORIZONTAL,
      VERTICAL,
      BOTH,
      UNKNOWN;

      public Cuboid.CuboidDirection opposite() {
         switch(this) {
         case NORTH:
            return SOUTH;
         case SOUTH:
            return NORTH;
         case EAST:
            return WEST;
         case WEST:
            return EAST;
         case DOWN:
            return UP;
         case UP:
            return DOWN;
         case HORIZONTAL:
            return VERTICAL;
         case VERTICAL:
            return HORIZONTAL;
         case BOTH:
            return BOTH;
         default:
            return UNKNOWN;
         }
      }
   }
}
