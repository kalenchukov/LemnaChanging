# Lemna Changing
Изменение значений полей класса.

### Создание изменяющего класса
Для создания изменяющего класса, необходимо создать класс реализующий интерфейс "Modificatory".

```java
import Changing;

public final class CommentModifier implements Modificatory<String>
{
	@Nullable
	@Override
	public String modify(@Nullable String value)
	{
		if (value == null) {
			return null;
		}

		return value.toUpperCase();
	}
}
```

### Изменение значений полей класса
Чтобы задать изменяющего для поля, необходимо добавить аннотацию "@Changer".
Данная аннотация содержит параметр для указания изменяющего класса.

Класс, в котором необходимо изменить значение поля:
```java
import dev.kalenchukov.lemna.changing.annotations.Changer;

public class Experimental
{
	@Changer(modifier = CommentModifier.class)
	private String comment = "Мой комментарий";

	public String getComment()
	{
		return this.comment;
	}
}
```

Вызов изменяющего:
```java
Experimental experimental = new Experimental();

Changeable changing = new Changing(experimental);
changing.change();

experimental.getComment();
/*
Результат выполнения: МОЙ КОММЕНТАРИЙ
*/