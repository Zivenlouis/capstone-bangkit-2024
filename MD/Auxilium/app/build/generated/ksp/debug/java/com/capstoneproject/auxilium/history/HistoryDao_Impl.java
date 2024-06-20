package com.capstoneproject.auxilium.history;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
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

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class HistoryDao_Impl implements HistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<History> __insertionAdapterOfHistory;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public HistoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHistory = new EntityInsertionAdapter<History>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `history` (`id`,`id1`,`id2`,`id3`,`id4`,`id5`,`id6`,`id7`,`id8`,`id9`,`id10`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final History entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getId1());
        statement.bindLong(3, entity.getId2());
        statement.bindLong(4, entity.getId3());
        statement.bindLong(5, entity.getId4());
        statement.bindLong(6, entity.getId5());
        statement.bindLong(7, entity.getId6());
        statement.bindLong(8, entity.getId7());
        statement.bindLong(9, entity.getId8());
        statement.bindLong(10, entity.getId9());
        statement.bindLong(11, entity.getId10());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM history WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final History history, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHistory.insert(history);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final int id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
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
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllHistory(final Continuation<? super List<History>> $completion) {
    final String _sql = "SELECT * FROM history";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<History>>() {
      @Override
      @NonNull
      public List<History> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfId1 = CursorUtil.getColumnIndexOrThrow(_cursor, "id1");
          final int _cursorIndexOfId2 = CursorUtil.getColumnIndexOrThrow(_cursor, "id2");
          final int _cursorIndexOfId3 = CursorUtil.getColumnIndexOrThrow(_cursor, "id3");
          final int _cursorIndexOfId4 = CursorUtil.getColumnIndexOrThrow(_cursor, "id4");
          final int _cursorIndexOfId5 = CursorUtil.getColumnIndexOrThrow(_cursor, "id5");
          final int _cursorIndexOfId6 = CursorUtil.getColumnIndexOrThrow(_cursor, "id6");
          final int _cursorIndexOfId7 = CursorUtil.getColumnIndexOrThrow(_cursor, "id7");
          final int _cursorIndexOfId8 = CursorUtil.getColumnIndexOrThrow(_cursor, "id8");
          final int _cursorIndexOfId9 = CursorUtil.getColumnIndexOrThrow(_cursor, "id9");
          final int _cursorIndexOfId10 = CursorUtil.getColumnIndexOrThrow(_cursor, "id10");
          final List<History> _result = new ArrayList<History>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final History _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpId1;
            _tmpId1 = _cursor.getInt(_cursorIndexOfId1);
            final int _tmpId2;
            _tmpId2 = _cursor.getInt(_cursorIndexOfId2);
            final int _tmpId3;
            _tmpId3 = _cursor.getInt(_cursorIndexOfId3);
            final int _tmpId4;
            _tmpId4 = _cursor.getInt(_cursorIndexOfId4);
            final int _tmpId5;
            _tmpId5 = _cursor.getInt(_cursorIndexOfId5);
            final int _tmpId6;
            _tmpId6 = _cursor.getInt(_cursorIndexOfId6);
            final int _tmpId7;
            _tmpId7 = _cursor.getInt(_cursorIndexOfId7);
            final int _tmpId8;
            _tmpId8 = _cursor.getInt(_cursorIndexOfId8);
            final int _tmpId9;
            _tmpId9 = _cursor.getInt(_cursorIndexOfId9);
            final int _tmpId10;
            _tmpId10 = _cursor.getInt(_cursorIndexOfId10);
            _item = new History(_tmpId,_tmpId1,_tmpId2,_tmpId3,_tmpId4,_tmpId5,_tmpId6,_tmpId7,_tmpId8,_tmpId9,_tmpId10);
            _result.add(_item);
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
