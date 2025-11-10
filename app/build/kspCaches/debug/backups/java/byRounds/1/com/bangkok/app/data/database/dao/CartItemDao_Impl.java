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
import com.bangkok.app.data.database.entities.CartItemEntity;
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
public final class CartItemDao_Impl implements CartItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CartItemEntity> __insertionAdapterOfCartItemEntity;

  private final EntityDeletionOrUpdateAdapter<CartItemEntity> __deletionAdapterOfCartItemEntity;

  private final EntityDeletionOrUpdateAdapter<CartItemEntity> __updateAdapterOfCartItemEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCartItemById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCartItemsByUser;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCartItemByUserAndProduct;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCartItems;

  public CartItemDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCartItemEntity = new EntityInsertionAdapter<CartItemEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `cart_items` (`id`,`userId`,`productId`,`quantity`,`addedAt`,`selectedSize`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CartItemEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getProductId());
        statement.bindLong(4, entity.getQuantity());
        statement.bindLong(5, entity.getAddedAt());
        if (entity.getSelectedSize() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSelectedSize());
        }
      }
    };
    this.__deletionAdapterOfCartItemEntity = new EntityDeletionOrUpdateAdapter<CartItemEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `cart_items` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CartItemEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfCartItemEntity = new EntityDeletionOrUpdateAdapter<CartItemEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `cart_items` SET `id` = ?,`userId` = ?,`productId` = ?,`quantity` = ?,`addedAt` = ?,`selectedSize` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CartItemEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getProductId());
        statement.bindLong(4, entity.getQuantity());
        statement.bindLong(5, entity.getAddedAt());
        if (entity.getSelectedSize() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSelectedSize());
        }
        statement.bindString(7, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteCartItemById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM cart_items WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllCartItemsByUser = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM cart_items WHERE userId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteCartItemByUserAndProduct = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM cart_items WHERE userId = ? AND productId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllCartItems = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM cart_items";
        return _query;
      }
    };
  }

  @Override
  public Object insertCartItem(final CartItemEntity cartItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCartItemEntity.insert(cartItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCartItem(final CartItemEntity cartItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCartItemEntity.handle(cartItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateCartItem(final CartItemEntity cartItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCartItemEntity.handle(cartItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCartItemById(final String cartItemId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCartItemById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, cartItemId);
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
          __preparedStmtOfDeleteCartItemById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllCartItemsByUser(final String userId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCartItemsByUser.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, userId);
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
          __preparedStmtOfDeleteAllCartItemsByUser.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCartItemByUserAndProduct(final String userId, final String productId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCartItemByUserAndProduct.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, userId);
        _argIndex = 2;
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
          __preparedStmtOfDeleteCartItemByUserAndProduct.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllCartItems(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCartItems.acquire();
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
          __preparedStmtOfDeleteAllCartItems.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<CartItemEntity>> getCartItemsByUser(final String userId) {
    final String _sql = "SELECT * FROM cart_items WHERE userId = ? ORDER BY addedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cart_items"}, new Callable<List<CartItemEntity>>() {
      @Override
      @NonNull
      public List<CartItemEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final int _cursorIndexOfSelectedSize = CursorUtil.getColumnIndexOrThrow(_cursor, "selectedSize");
          final List<CartItemEntity> _result = new ArrayList<CartItemEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CartItemEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpProductId;
            _tmpProductId = _cursor.getString(_cursorIndexOfProductId);
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            final String _tmpSelectedSize;
            if (_cursor.isNull(_cursorIndexOfSelectedSize)) {
              _tmpSelectedSize = null;
            } else {
              _tmpSelectedSize = _cursor.getString(_cursorIndexOfSelectedSize);
            }
            _item = new CartItemEntity(_tmpId,_tmpUserId,_tmpProductId,_tmpQuantity,_tmpAddedAt,_tmpSelectedSize);
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
  public Object getCartItem(final String userId, final String productId,
      final Continuation<? super CartItemEntity> $completion) {
    final String _sql = "SELECT * FROM cart_items WHERE userId = ? AND productId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    _argIndex = 2;
    _statement.bindString(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CartItemEntity>() {
      @Override
      @Nullable
      public CartItemEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final int _cursorIndexOfSelectedSize = CursorUtil.getColumnIndexOrThrow(_cursor, "selectedSize");
          final CartItemEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpProductId;
            _tmpProductId = _cursor.getString(_cursorIndexOfProductId);
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            final String _tmpSelectedSize;
            if (_cursor.isNull(_cursorIndexOfSelectedSize)) {
              _tmpSelectedSize = null;
            } else {
              _tmpSelectedSize = _cursor.getString(_cursorIndexOfSelectedSize);
            }
            _result = new CartItemEntity(_tmpId,_tmpUserId,_tmpProductId,_tmpQuantity,_tmpAddedAt,_tmpSelectedSize);
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
  public Object getCartItem(final String userId, final String productId, final String size,
      final Continuation<? super CartItemEntity> $completion) {
    final String _sql = "SELECT * FROM cart_items WHERE userId = ? AND productId = ? AND (selectedSize = ? OR (selectedSize IS NULL AND ? IS NULL))";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    _argIndex = 2;
    _statement.bindString(_argIndex, productId);
    _argIndex = 3;
    if (size == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, size);
    }
    _argIndex = 4;
    if (size == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, size);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CartItemEntity>() {
      @Override
      @Nullable
      public CartItemEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final int _cursorIndexOfSelectedSize = CursorUtil.getColumnIndexOrThrow(_cursor, "selectedSize");
          final CartItemEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpProductId;
            _tmpProductId = _cursor.getString(_cursorIndexOfProductId);
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            final String _tmpSelectedSize;
            if (_cursor.isNull(_cursorIndexOfSelectedSize)) {
              _tmpSelectedSize = null;
            } else {
              _tmpSelectedSize = _cursor.getString(_cursorIndexOfSelectedSize);
            }
            _result = new CartItemEntity(_tmpId,_tmpUserId,_tmpProductId,_tmpQuantity,_tmpAddedAt,_tmpSelectedSize);
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
  public Object getCartItemById(final String cartItemId,
      final Continuation<? super CartItemEntity> $completion) {
    final String _sql = "SELECT * FROM cart_items WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, cartItemId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CartItemEntity>() {
      @Override
      @Nullable
      public CartItemEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final int _cursorIndexOfSelectedSize = CursorUtil.getColumnIndexOrThrow(_cursor, "selectedSize");
          final CartItemEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpProductId;
            _tmpProductId = _cursor.getString(_cursorIndexOfProductId);
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            final String _tmpSelectedSize;
            if (_cursor.isNull(_cursorIndexOfSelectedSize)) {
              _tmpSelectedSize = null;
            } else {
              _tmpSelectedSize = _cursor.getString(_cursorIndexOfSelectedSize);
            }
            _result = new CartItemEntity(_tmpId,_tmpUserId,_tmpProductId,_tmpQuantity,_tmpAddedAt,_tmpSelectedSize);
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
  public Object getCartItemCount(final String userId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM cart_items WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
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
