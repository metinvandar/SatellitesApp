import com.metinvandar.satellitesapp.common.Result
import com.metinvandar.satellitesapp.data.exception.BaseException
import com.metinvandar.satellitesapp.data.exception.SatellitesNotFoundException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException


fun <T> Flow<Result<T>>.emitErrorResult(): Flow<Result<T>> = catch { throwable ->
    when(throwable) {
        is IOException -> emit(Result.Error(SatellitesNotFoundException()))
        else -> emit(Result.Error(BaseException(throwable)))
    }
}
