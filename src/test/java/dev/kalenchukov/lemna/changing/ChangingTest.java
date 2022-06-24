/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.changing;

import dev.kalenchukov.lemna.changing.annotations.Changer;
import dev.kalenchukov.lemna.changing.supports.CommentModifier;
import dev.kalenchukov.lemna.changing.supports.CommentNullModifier;
import dev.kalenchukov.lemna.changing.supports.PriceModifier;
import dev.kalenchukov.lemna.changing.supports.QuoteModifier;
import org.junit.Test;

import static org.junit.Assert.*;

public final class ChangingTest
{
    /**
     * Проверка изменения значений нескольких полей класса.
     */
    @Test
    public void testChange1()
    {
        class Experimental
        {
			@Changer(modifier = CommentModifier.class)
            private String comment = "Мой комментарий";

			@Changer(modifier = PriceModifier.class)
			private Double price = 20.05;

			public String getComment()
			{
				return this.comment;
			}

			public Double getPrice()
			{
				return this.price;
			}
		}

        Experimental experimental = new Experimental();

        Changeable fieldValueChanger = new Changing(experimental);
		fieldValueChanger.change();

        assertEquals("МОЙ КОММЕНТАРИЙ к классу 'Experimental'", experimental.getComment());
		assertEquals(Double.valueOf(33.95), experimental.getPrice());
    }

	/**
	 * Проверка изменения значения с помощью нескольких изменяющих.
	 */
	@Test
	public void testChange2()
	{
		class Experimental
		{
			@Changer.ManyChanger({
				@Changer(modifier = CommentModifier.class),
				@Changer(modifier = QuoteModifier.class)
			})
			private String comment = "Мой комментарий";

			public String getComment()
			{
				return this.comment;
			}
		}

		Experimental experimental = new Experimental();

		Changeable fieldValueChanger = new Changing(experimental);
		fieldValueChanger.change();

		assertEquals("'МОЙ КОММЕНТАРИЙ к классу 'Experimental''", experimental.getComment());
	}

	/**
	 * Проверка изменения значения на значение {@code null}.
	 */
	@Test
	public void testChange3()
	{
		class Experimental
		{
			@Changer(modifier = CommentNullModifier.class)
			private String comment = "Мой комментарий";

			public String getComment()
			{
				return this.comment;
			}
		}

		Experimental experimental = new Experimental();

		Changeable fieldValueChanger = new Changing(experimental);
		fieldValueChanger.change();

		assertNull(experimental.getComment());
	}
}