package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.Theme;

public interface ThemeRepository extends AdvancedJpaRepository<Theme,Integer> {

	public Theme findByTheme(String theme);
}
