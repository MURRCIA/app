package com.bangkok.app.data.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.bangkok.app.data.database.converters.Converters;
import com.bangkok.app.data.database.entities.ProductEntity;
import com.bangkok.app.data.models.ProductCategory;
import com.bangkok.app.data.models.ProductSize;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ProductDao_Impl implements ProductDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProductEntity> __insertionAdapterOfProductEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<ProductEntity> __deletionAdapterOfProductEntity;

  private final EntityDeletionOrUpdateAdapter<ProductEntity> __updateAdapterOfProductEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteProductById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllProducts;

  public ProductDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProductEntity = new EntityInsertionAdapter<ProductEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `products` (`id`,`name`,`description`,`price`,`category`,`imageUrl`,`tags`,`isFeatured`,`isNewArrival`,`discountPercentage`,`detailedDescription`,`availableSizes`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getDescription());
        statement.bindDouble(4, entity.getPrice());
        final String _tmp = __converters.fromProductCategory(entity.getCategory());
        statement.bindString(5, _tmp);
        if (entity.getImageUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getImageUrl());
        }
        final String _tmp_1 = __converters.fromStringList(entity.getTags());
        statement.bindString(7, _tmp_1);
        final int _tmp_2 = entity.isFeatured() ? 1 : 0;
        statement.bindLong(8, _tmp_2);
        final int _tmp_3 = entity.isNewArrival() ? 1 : 0;
        statement.bindLong(9, _tmp_3);
        if (entity.getDiscountPercentage() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getDiscountPercentage());
        }
        if (entity.getDetailedDescription() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getDetailedDescription());
        }
        final String _tmp_4 = __converters.fromProductSizeList(entity.getAvailableSizes());
        statement.bindString(12, _tmp_4);
        statement.bindLong(13, entity.getCreatedAt());
        statement.bindLong(14, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfProductEntity = new EntityDeletionOrUpdateAdapter<ProductEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `products` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfProductEntity = new EntityDeletionOrUpdateAdapter<ProductEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `products` SET `id` = ?,`name` = ?,`description` = ?,`price` = ?,`category` = ?,`imageUrl` = ?,`tags` = ?,`isFeatured` = ?,`isNewArrival` = ?,`discountPercentage` = ?,`detailedDescription` = ?,`availableSizes` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getDescription());
        statement.bindDouble(4, entity.getPrice());
        final String _tmp = __converters.fromProductCategory(entity.getCategory());
        statement.bindString(5, _tmp);
        if (entity.getImageUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getImageUrl());
        }
        final String _tmp_1 = __converters.fromStringList(entity.getTags());
        statement.bindString(7, _tmp_1);
        final int _tmp_2 = entity.isFeatured() ? 1 : 0;
        statement.bindLong(8, _tmp_2);
        final int _tmp_3 = entity.isNewArrival() ? 1 : 0;
        statement.bindLong(9, _tmp_3);
        if (entity.getDiscountPercentage() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getDiscountPercentage());
        }
        if (entity.getDetailedDescription() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getDetailedDescription());
        }
        final String _tmp_4 = __converters.fromProductSizeList(entity.getAvailableSizes());
        statement.bindString(12, _tmp_4);
        statement.bindLong(13, entity.getCreatedAt());
        statement.bindLong(14, entity.getUpdatedAt());
        statement.bindString(15, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteProductById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM products WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllProducts = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM products";
        return _query;
      }
    };
  }

  @Override
  public Object insertProduct(final ProductEntity product,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProductEntity.insert(product);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertProducts(final List<ProductEntity> products,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProductEntity.insert(products);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteProduct(final ProductEntity product,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfProductEntity.handle(product);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateProduct(final ProductEntity product,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfProductEntity.handle(product);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteProductById(final String productId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteProductById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, productId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteProductById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllProducts(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllProducts.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllProducts.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ProductEntity>> getAllProducts() {
    final String _sql = "SELECT * FROM products ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfIsFeatured = CursorUtil.getColumnIndexOrThrow(_cursor, "isFeatured");
          final int _cursorIndexOfIsNewArrival = CursorUtil.getColumnIndexOrThrow(_cursor, "isNewArrival");
          final int _cursorIndexOfDiscountPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "discountPercentage");
          final int _cursorIndexOfDetailedDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "detailedDescription");
          final int _cursorIndexOfAvailableSizes = CursorUtil.getColumnIndexOrThrow(_cursor, "availableSizes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final ProductCategory _tmpCategory;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toProductCategory(_tmp);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final List<String> _tmpTags;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_1);
            final boolean _tmpIsFeatured;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFeatured);
            _tmpIsFeatured = _tmp_2 != 0;
            final boolean _tmpIsNewArrival;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsNewArrival);
            _tmpIsNewArrival = _tmp_3 != 0;
            final Integer _tmpDiscountPercentage;
            if (_cursor.isNull(_cursorIndexOfDiscountPercentage)) {
              _tmpDiscountPercentage = null;
            } else {
              _tmpDiscountPercentage = _cursor.getInt(_cursorIndexOfDiscountPercentage);
            }
            final String _tmpDetailedDescription;
            if (_cursor.isNull(_cursorIndexOfDetailedDescription)) {
              _tmpDetailedDescription = null;
            } else {
              _tmpDetailedDescription = _cursor.getString(_cursorIndexOfDetailedDescription);
            }
            final List<ProductSize> _tmpAvailableSizes;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfAvailableSizes);
            _tmpAvailableSizes = __converters.toProductSizeList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPrice,_tmpCategory,_tmpImageUrl,_tmpTags,_tmpIsFeatured,_tmpIsNewArrival,_tmpDiscountPercentage,_tmpDetailedDescription,_tmpAvailableSizes,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getProductById(final String productId,
      final Continuation<? super ProductEntity> $completion) {
    final String _sql = "SELECT * FROM products WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ProductEntity>() {
      @Override
      @Nullable
      public ProductEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfIsFeatured = CursorUtil.getColumnIndexOrThrow(_cursor, "isFeatured");
          final int _cursorIndexOfIsNewArrival = CursorUtil.getColumnIndexOrThrow(_cursor, "isNewArrival");
          final int _cursorIndexOfDiscountPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "discountPercentage");
          final int _cursorIndexOfDetailedDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "detailedDescription");
          final int _cursorIndexOfAvailableSizes = CursorUtil.getColumnIndexOrThrow(_cursor, "availableSizes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final ProductEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final ProductCategory _tmpCategory;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toProductCategory(_tmp);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final List<String> _tmpTags;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_1);
            final boolean _tmpIsFeatured;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFeatured);
            _tmpIsFeatured = _tmp_2 != 0;
            final boolean _tmpIsNewArrival;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsNewArrival);
            _tmpIsNewArrival = _tmp_3 != 0;
            final Integer _tmpDiscountPercentage;
            if (_cursor.isNull(_cursorIndexOfDiscountPercentage)) {
              _tmpDiscountPercentage = null;
            } else {
              _tmpDiscountPercentage = _cursor.getInt(_cursorIndexOfDiscountPercentage);
            }
            final String _tmpDetailedDescription;
            if (_cursor.isNull(_cursorIndexOfDetailedDescription)) {
              _tmpDetailedDescription = null;
            } else {
              _tmpDetailedDescription = _cursor.getString(_cursorIndexOfDetailedDescription);
            }
            final List<ProductSize> _tmpAvailableSizes;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfAvailableSizes);
            _tmpAvailableSizes = __converters.toProductSizeList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPrice,_tmpCategory,_tmpImageUrl,_tmpTags,_tmpIsFeatured,_tmpIsNewArrival,_tmpDiscountPercentage,_tmpDetailedDescription,_tmpAvailableSizes,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ProductEntity>> getProductsByCategory(final ProductCategory category) {
    final String _sql = "SELECT * FROM products WHERE category = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromProductCategory(category);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfIsFeatured = CursorUtil.getColumnIndexOrThrow(_cursor, "isFeatured");
          final int _cursorIndexOfIsNewArrival = CursorUtil.getColumnIndexOrThrow(_cursor, "isNewArrival");
          final int _cursorIndexOfDiscountPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "discountPercentage");
          final int _cursorIndexOfDetailedDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "detailedDescription");
          final int _cursorIndexOfAvailableSizes = CursorUtil.getColumnIndexOrThrow(_cursor, "availableSizes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final ProductCategory _tmpCategory;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toProductCategory(_tmp_1);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final List<String> _tmpTags;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_2);
            final boolean _tmpIsFeatured;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFeatured);
            _tmpIsFeatured = _tmp_3 != 0;
            final boolean _tmpIsNewArrival;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsNewArrival);
            _tmpIsNewArrival = _tmp_4 != 0;
            final Integer _tmpDiscountPercentage;
            if (_cursor.isNull(_cursorIndexOfDiscountPercentage)) {
              _tmpDiscountPercentage = null;
            } else {
              _tmpDiscountPercentage = _cursor.getInt(_cursorIndexOfDiscountPercentage);
            }
            final String _tmpDetailedDescription;
            if (_cursor.isNull(_cursorIndexOfDetailedDescription)) {
              _tmpDetailedDescription = null;
            } else {
              _tmpDetailedDescription = _cursor.getString(_cursorIndexOfDetailedDescription);
            }
            final List<ProductSize> _tmpAvailableSizes;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfAvailableSizes);
            _tmpAvailableSizes = __converters.toProductSizeList(_tmp_5);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPrice,_tmpCategory,_tmpImageUrl,_tmpTags,_tmpIsFeatured,_tmpIsNewArrival,_tmpDiscountPercentage,_tmpDetailedDescription,_tmpAvailableSizes,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ProductEntity>> getProductsByCategoryExcluding(final ProductCategory category,
      final String excludeId, final int limit) {
    final String _sql = "SELECT * FROM products WHERE category = ? AND id != ? ORDER BY createdAt DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    final String _tmp = __converters.fromProductCategory(category);
    _statement.bindString(_argIndex, _tmp);
    _argIndex = 2;
    _statement.bindString(_argIndex, excludeId);
    _argIndex = 3;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfIsFeatured = CursorUtil.getColumnIndexOrThrow(_cursor, "isFeatured");
          final int _cursorIndexOfIsNewArrival = CursorUtil.getColumnIndexOrThrow(_cursor, "isNewArrival");
          final int _cursorIndexOfDiscountPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "discountPercentage");
          final int _cursorIndexOfDetailedDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "detailedDescription");
          final int _cursorIndexOfAvailableSizes = CursorUtil.getColumnIndexOrThrow(_cursor, "availableSizes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final ProductCategory _tmpCategory;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toProductCategory(_tmp_1);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final List<String> _tmpTags;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_2);
            final boolean _tmpIsFeatured;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFeatured);
            _tmpIsFeatured = _tmp_3 != 0;
            final boolean _tmpIsNewArrival;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsNewArrival);
            _tmpIsNewArrival = _tmp_4 != 0;
            final Integer _tmpDiscountPercentage;
            if (_cursor.isNull(_cursorIndexOfDiscountPercentage)) {
              _tmpDiscountPercentage = null;
            } else {
              _tmpDiscountPercentage = _cursor.getInt(_cursorIndexOfDiscountPercentage);
            }
            final String _tmpDetailedDescription;
            if (_cursor.isNull(_cursorIndexOfDetailedDescription)) {
              _tmpDetailedDescription = null;
            } else {
              _tmpDetailedDescription = _cursor.getString(_cursorIndexOfDetailedDescription);
            }
            final List<ProductSize> _tmpAvailableSizes;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfAvailableSizes);
            _tmpAvailableSizes = __converters.toProductSizeList(_tmp_5);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPrice,_tmpCategory,_tmpImageUrl,_tmpTags,_tmpIsFeatured,_tmpIsNewArrival,_tmpDiscountPercentage,_tmpDetailedDescription,_tmpAvailableSizes,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ProductEntity>> getFeaturedProducts() {
    final String _sql = "SELECT * FROM products WHERE isFeatured = 1 ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfIsFeatured = CursorUtil.getColumnIndexOrThrow(_cursor, "isFeatured");
          final int _cursorIndexOfIsNewArrival = CursorUtil.getColumnIndexOrThrow(_cursor, "isNewArrival");
          final int _cursorIndexOfDiscountPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "discountPercentage");
          final int _cursorIndexOfDetailedDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "detailedDescription");
          final int _cursorIndexOfAvailableSizes = CursorUtil.getColumnIndexOrThrow(_cursor, "availableSizes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final ProductCategory _tmpCategory;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toProductCategory(_tmp);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final List<String> _tmpTags;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_1);
            final boolean _tmpIsFeatured;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFeatured);
            _tmpIsFeatured = _tmp_2 != 0;
            final boolean _tmpIsNewArrival;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsNewArrival);
            _tmpIsNewArrival = _tmp_3 != 0;
            final Integer _tmpDiscountPercentage;
            if (_cursor.isNull(_cursorIndexOfDiscountPercentage)) {
              _tmpDiscountPercentage = null;
            } else {
              _tmpDiscountPercentage = _cursor.getInt(_cursorIndexOfDiscountPercentage);
            }
            final String _tmpDetailedDescription;
            if (_cursor.isNull(_cursorIndexOfDetailedDescription)) {
              _tmpDetailedDescription = null;
            } else {
              _tmpDetailedDescription = _cursor.getString(_cursorIndexOfDetailedDescription);
            }
            final List<ProductSize> _tmpAvailableSizes;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfAvailableSizes);
            _tmpAvailableSizes = __converters.toProductSizeList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPrice,_tmpCategory,_tmpImageUrl,_tmpTags,_tmpIsFeatured,_tmpIsNewArrival,_tmpDiscountPercentage,_tmpDetailedDescription,_tmpAvailableSizes,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ProductEntity>> getNewArrivals() {
    final String _sql = "SELECT * FROM products WHERE isNewArrival = 1 ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfIsFeatured = CursorUtil.getColumnIndexOrThrow(_cursor, "isFeatured");
          final int _cursorIndexOfIsNewArrival = CursorUtil.getColumnIndexOrThrow(_cursor, "isNewArrival");
          final int _cursorIndexOfDiscountPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "discountPercentage");
          final int _cursorIndexOfDetailedDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "detailedDescription");
          final int _cursorIndexOfAvailableSizes = CursorUtil.getColumnIndexOrThrow(_cursor, "availableSizes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final ProductCategory _tmpCategory;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toProductCategory(_tmp);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final List<String> _tmpTags;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_1);
            final boolean _tmpIsFeatured;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFeatured);
            _tmpIsFeatured = _tmp_2 != 0;
            final boolean _tmpIsNewArrival;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsNewArrival);
            _tmpIsNewArrival = _tmp_3 != 0;
            final Integer _tmpDiscountPercentage;
            if (_cursor.isNull(_cursorIndexOfDiscountPercentage)) {
              _tmpDiscountPercentage = null;
            } else {
              _tmpDiscountPercentage = _cursor.getInt(_cursorIndexOfDiscountPercentage);
            }
            final String _tmpDetailedDescription;
            if (_cursor.isNull(_cursorIndexOfDetailedDescription)) {
              _tmpDetailedDescription = null;
            } else {
              _tmpDetailedDescription = _cursor.getString(_cursorIndexOfDetailedDescription);
            }
            final List<ProductSize> _tmpAvailableSizes;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfAvailableSizes);
            _tmpAvailableSizes = __converters.toProductSizeList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPrice,_tmpCategory,_tmpImageUrl,_tmpTags,_tmpIsFeatured,_tmpIsNewArrival,_tmpDiscountPercentage,_tmpDetailedDescription,_tmpAvailableSizes,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ProductEntity>> searchProducts(final String query) {
    final String _sql = "SELECT * FROM products WHERE name LIKE ? OR description LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfIsFeatured = CursorUtil.getColumnIndexOrThrow(_cursor, "isFeatured");
          final int _cursorIndexOfIsNewArrival = CursorUtil.getColumnIndexOrThrow(_cursor, "isNewArrival");
          final int _cursorIndexOfDiscountPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "discountPercentage");
          final int _cursorIndexOfDetailedDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "detailedDescription");
          final int _cursorIndexOfAvailableSizes = CursorUtil.getColumnIndexOrThrow(_cursor, "availableSizes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final ProductCategory _tmpCategory;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toProductCategory(_tmp);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final List<String> _tmpTags;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_1);
            final boolean _tmpIsFeatured;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFeatured);
            _tmpIsFeatured = _tmp_2 != 0;
            final boolean _tmpIsNewArrival;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsNewArrival);
            _tmpIsNewArrival = _tmp_3 != 0;
            final Integer _tmpDiscountPercentage;
            if (_cursor.isNull(_cursorIndexOfDiscountPercentage)) {
              _tmpDiscountPercentage = null;
            } else {
              _tmpDiscountPercentage = _cursor.getInt(_cursorIndexOfDiscountPercentage);
            }
            final String _tmpDetailedDescription;
            if (_cursor.isNull(_cursorIndexOfDetailedDescription)) {
              _tmpDetailedDescription = null;
            } else {
              _tmpDetailedDescription = _cursor.getString(_cursorIndexOfDetailedDescription);
            }
            final List<ProductSize> _tmpAvailableSizes;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfAvailableSizes);
            _tmpAvailableSizes = __converters.toProductSizeList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPrice,_tmpCategory,_tmpImageUrl,_tmpTags,_tmpIsFeatured,_tmpIsNewArrival,_tmpDiscountPercentage,_tmpDetailedDescription,_tmpAvailableSizes,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ProductEntity>> getProductsOnSale() {
    final String _sql = "SELECT * FROM products WHERE discountPercentage IS NOT NULL AND discountPercentage > 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfIsFeatured = CursorUtil.getColumnIndexOrThrow(_cursor, "isFeatured");
          final int _cursorIndexOfIsNewArrival = CursorUtil.getColumnIndexOrThrow(_cursor, "isNewArrival");
          final int _cursorIndexOfDiscountPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "discountPercentage");
          final int _cursorIndexOfDetailedDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "detailedDescription");
          final int _cursorIndexOfAvailableSizes = CursorUtil.getColumnIndexOrThrow(_cursor, "availableSizes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final ProductCategory _tmpCategory;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toProductCategory(_tmp);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final List<String> _tmpTags;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_1);
            final boolean _tmpIsFeatured;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFeatured);
            _tmpIsFeatured = _tmp_2 != 0;
            final boolean _tmpIsNewArrival;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsNewArrival);
            _tmpIsNewArrival = _tmp_3 != 0;
            final Integer _tmpDiscountPercentage;
            if (_cursor.isNull(_cursorIndexOfDiscountPercentage)) {
              _tmpDiscountPercentage = null;
            } else {
              _tmpDiscountPercentage = _cursor.getInt(_cursorIndexOfDiscountPercentage);
            }
            final String _tmpDetailedDescription;
            if (_cursor.isNull(_cursorIndexOfDetailedDescription)) {
              _tmpDetailedDescription = null;
            } else {
              _tmpDetailedDescription = _cursor.getString(_cursorIndexOfDetailedDescription);
            }
            final List<ProductSize> _tmpAvailableSizes;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfAvailableSizes);
            _tmpAvailableSizes = __converters.toProductSizeList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPrice,_tmpCategory,_tmpImageUrl,_tmpTags,_tmpIsFeatured,_tmpIsNewArrival,_tmpDiscountPercentage,_tmpDetailedDescription,_tmpAvailableSizes,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getProductCountByCategory(final ProductCategory category,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM products WHERE category = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromProductCategory(category);
    _statement.bindString(_argIndex, _tmp);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(0);
            _result = _tmp_1;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getProductCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM products";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
