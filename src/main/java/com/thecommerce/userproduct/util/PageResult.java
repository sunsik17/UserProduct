package com.thecommerce.userproduct.util;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
	public static final Long NON_KEY = -1L;

	private List<T> body;
	private boolean hasNext;
}
