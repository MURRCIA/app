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
import com.bangkok.app.data.database.entities.UserEntity;
import com.bangkok.app.data.models.UserPreferences;
import com.bangkok.app.data.models.UserRole;
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
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserEntity> __insertionAdapterOfUserEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<UserEntity> __deletionAdapterOfUserEntity;

  private final EntityDeletionOrUpdateAdapter<UserEntity> __updateAdapterOfUserEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUserById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllUsers;

  public UserDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `users` (`id`,`fullName`,`email`,`password`,`phone`,`profileImageUrl`,`registrationDate`,`isEmailVerified`,`role`,`preferences`,`createdAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getFullName());
        statement.bindString(3, entity.getEmail());
        statement.bindString(4, entity.getPassword());
        statement.bindString(5, entity.getPhone());
        if (entity.getProfileImageUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getProfileImageUrl());
        }
        statement.bindString(7, entity.getRegistrationDate());
        final int _tmp = entity.isEmailVerified() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final String _tmp_1 = __converters.fromUserRole(entity.getRole());
        statement.bindString(9, _tmp_1);
        final String _tmp_2 = __converters.fromUserPreferences(entity.getPreferences());
        statement.bindString(10, _tmp_2);
        statement.bindLong(11, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `users` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `users` SET `id` = ?,`fullName` = ?,`email` = ?,`password` = ?,`phone` = ?,`profileImageUrl` = ?,`registrationDate` = ?,`isEmailVerified` = ?,`role` = ?,`preferences` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getFullName());
        statement.bindString(3, entity.getEmail());
        statement.bindString(4, entity.getPassword());
        statement.bindString(5, entity.getPhone());
        if (entity.getProfileImageUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getProfileImageUrl());
        }
        statement.bindString(7, entity.getRegistrationDate());
        final int _tmp = entity.isEmailVerified() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final String _tmp_1 = __converters.fromUserRole(entity.getRole());
        statement.bindString(9, _tmp_1);
        final String _tmp_2 = __converters.fromUserPreferences(entity.getPreferences());
        statement.bindString(10, _tmp_2);
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindString(12, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteUserById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM users WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllUsers = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM users";
        return _query;
      }
    };
  }

  @Override
  public Object insertUser(final UserEntity user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserEntity.insert(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertUsers(final List<UserEntity> users,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserEntity.insert(users);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteUser(final UserEntity user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfUserEntity.handle(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateUser(final UserEntity user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUserEntity.handle(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteUserById(final String userId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUserById.acquire();
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
          __preparedStmtOfDeleteUserById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllUsers(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllUsers.acquire();
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
          __preparedStmtOfDeleteAllUsers.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<UserEntity>> getAllUsers() {
    final String _sql = "SELECT * FROM users";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"users"}, new Callable<List<UserEntity>>() {
      @Override
      @NonNull
      public List<UserEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfProfileImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "profileImageUrl");
          final int _cursorIndexOfRegistrationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "registrationDate");
          final int _cursorIndexOfIsEmailVerified = CursorUtil.getColumnIndexOrThrow(_cursor, "isEmailVerified");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfPreferences = CursorUtil.getColumnIndexOrThrow(_cursor, "preferences");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<UserEntity> _result = new ArrayList<UserEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPassword;
            _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpProfileImageUrl;
            if (_cursor.isNull(_cursorIndexOfProfileImageUrl)) {
              _tmpProfileImageUrl = null;
            } else {
              _tmpProfileImageUrl = _cursor.getString(_cursorIndexOfProfileImageUrl);
            }
            final String _tmpRegistrationDate;
            _tmpRegistrationDate = _cursor.getString(_cursorIndexOfRegistrationDate);
            final boolean _tmpIsEmailVerified;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEmailVerified);
            _tmpIsEmailVerified = _tmp != 0;
            final UserRole _tmpRole;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRole);
            _tmpRole = __converters.toUserRole(_tmp_1);
            final UserPreferences _tmpPreferences;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfPreferences);
            _tmpPreferences = __converters.toUserPreferences(_tmp_2);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new UserEntity(_tmpId,_tmpFullName,_tmpEmail,_tmpPassword,_tmpPhone,_tmpProfileImageUrl,_tmpRegistrationDate,_tmpIsEmailVerified,_tmpRole,_tmpPreferences,_tmpCreatedAt);
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
  public Object getUserById(final String userId,
      final Continuation<? super UserEntity> $completion) {
    final String _sql = "SELECT * FROM users WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfProfileImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "profileImageUrl");
          final int _cursorIndexOfRegistrationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "registrationDate");
          final int _cursorIndexOfIsEmailVerified = CursorUtil.getColumnIndexOrThrow(_cursor, "isEmailVerified");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfPreferences = CursorUtil.getColumnIndexOrThrow(_cursor, "preferences");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPassword;
            _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpProfileImageUrl;
            if (_cursor.isNull(_cursorIndexOfProfileImageUrl)) {
              _tmpProfileImageUrl = null;
            } else {
              _tmpProfileImageUrl = _cursor.getString(_cursorIndexOfProfileImageUrl);
            }
            final String _tmpRegistrationDate;
            _tmpRegistrationDate = _cursor.getString(_cursorIndexOfRegistrationDate);
            final boolean _tmpIsEmailVerified;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEmailVerified);
            _tmpIsEmailVerified = _tmp != 0;
            final UserRole _tmpRole;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRole);
            _tmpRole = __converters.toUserRole(_tmp_1);
            final UserPreferences _tmpPreferences;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfPreferences);
            _tmpPreferences = __converters.toUserPreferences(_tmp_2);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new UserEntity(_tmpId,_tmpFullName,_tmpEmail,_tmpPassword,_tmpPhone,_tmpProfileImageUrl,_tmpRegistrationDate,_tmpIsEmailVerified,_tmpRole,_tmpPreferences,_tmpCreatedAt);
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
  public Object getUserByEmail(final String email,
      final Continuation<? super UserEntity> $completion) {
    final String _sql = "SELECT * FROM users WHERE email = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, email);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfProfileImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "profileImageUrl");
          final int _cursorIndexOfRegistrationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "registrationDate");
          final int _cursorIndexOfIsEmailVerified = CursorUtil.getColumnIndexOrThrow(_cursor, "isEmailVerified");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfPreferences = CursorUtil.getColumnIndexOrThrow(_cursor, "preferences");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPassword;
            _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpProfileImageUrl;
            if (_cursor.isNull(_cursorIndexOfProfileImageUrl)) {
              _tmpProfileImageUrl = null;
            } else {
              _tmpProfileImageUrl = _cursor.getString(_cursorIndexOfProfileImageUrl);
            }
            final String _tmpRegistrationDate;
            _tmpRegistrationDate = _cursor.getString(_cursorIndexOfRegistrationDate);
            final boolean _tmpIsEmailVerified;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEmailVerified);
            _tmpIsEmailVerified = _tmp != 0;
            final UserRole _tmpRole;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRole);
            _tmpRole = __converters.toUserRole(_tmp_1);
            final UserPreferences _tmpPreferences;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfPreferences);
            _tmpPreferences = __converters.toUserPreferences(_tmp_2);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new UserEntity(_tmpId,_tmpFullName,_tmpEmail,_tmpPassword,_tmpPhone,_tmpProfileImageUrl,_tmpRegistrationDate,_tmpIsEmailVerified,_tmpRole,_tmpPreferences,_tmpCreatedAt);
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
  public Object loginUser(final String email, final String password,
      final Continuation<? super UserEntity> $completion) {
    final String _sql = "SELECT * FROM users WHERE email = ? AND password = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, email);
    _argIndex = 2;
    _statement.bindString(_argIndex, password);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfProfileImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "profileImageUrl");
          final int _cursorIndexOfRegistrationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "registrationDate");
          final int _cursorIndexOfIsEmailVerified = CursorUtil.getColumnIndexOrThrow(_cursor, "isEmailVerified");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfPreferences = CursorUtil.getColumnIndexOrThrow(_cursor, "preferences");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPassword;
            _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpProfileImageUrl;
            if (_cursor.isNull(_cursorIndexOfProfileImageUrl)) {
              _tmpProfileImageUrl = null;
            } else {
              _tmpProfileImageUrl = _cursor.getString(_cursorIndexOfProfileImageUrl);
            }
            final String _tmpRegistrationDate;
            _tmpRegistrationDate = _cursor.getString(_cursorIndexOfRegistrationDate);
            final boolean _tmpIsEmailVerified;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEmailVerified);
            _tmpIsEmailVerified = _tmp != 0;
            final UserRole _tmpRole;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRole);
            _tmpRole = __converters.toUserRole(_tmp_1);
            final UserPreferences _tmpPreferences;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfPreferences);
            _tmpPreferences = __converters.toUserPreferences(_tmp_2);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new UserEntity(_tmpId,_tmpFullName,_tmpEmail,_tmpPassword,_tmpPhone,_tmpProfileImageUrl,_tmpRegistrationDate,_tmpIsEmailVerified,_tmpRole,_tmpPreferences,_tmpCreatedAt);
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
  public Object getUserCountByEmail(final String email,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM users WHERE email = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, email);
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
