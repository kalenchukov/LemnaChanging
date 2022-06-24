/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.changing.supports;

import dev.kalenchukov.lemna.changing.interfaces.Modificatory;
import org.jetbrains.annotations.Nullable;

public final class CommentNullModifier implements Modificatory<String>
{
	@Nullable
	@Override
	public String modify(@Nullable final String value)
	{
		return null;
	}
}
