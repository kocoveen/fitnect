// src/utils/search.js

export const filterGyms = (gyms, keyword, selectedTypes) => {
  return gyms.filter((gym) => {
    const matchesKeyword = !keyword || gym.name.includes(keyword) || gym.type.includes(keyword);
    const matchesType = selectedTypes.length === 0 || selectedTypes.includes(gym.type);
    return matchesKeyword && matchesType;
  });
};
