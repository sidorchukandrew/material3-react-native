import NativeColors, { type MaterialThemes } from '../specs/NativeColors';

let cachedColors: MaterialThemes;

export const Colors = {
  getColors: () => {
    if (!cachedColors) {
      cachedColors = NativeColors.getColors();
    }

    return cachedColors;
  },
};
