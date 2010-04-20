package com.thoughtworks.mingle.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.test.suitebuilder.annotation.SmallTest;

import com.thoughtworks.mingle.domain.Murmur;

import junit.framework.TestCase;

public class MurmursTest extends TestCase {

	private Murmur murmur;
	private SimpleDateFormat format;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		murmur = new Murmur();
		format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
	}

	@SmallTest
	public void testSetCreatedDate() throws Exception {
		Date expectedDate = new Date(2010, 4, 10, 10, 10, 10);
		murmur.setCreatedAtFrom(format.format(expectedDate));
		Date createdAt = murmur.getCreatedAt();

		assertEquals(expectedDate, createdAt);
	}
}