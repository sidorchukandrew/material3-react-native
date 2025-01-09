import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface MaterialThemes {
  light: MaterialColors;
  dark: MaterialColors;
}

export interface MaterialColors {
  errorContainer: string;
  onBackground: string;
  onError: string;
  onErrorContainer: string;
  onPrimary: string;
  onPrimaryContainer: string;
  onPrimaryFixed: string;
  onPrimaryFixedVariant: string;
  onPrimarySurface: string;
  onSecondary: string;
  onSecondaryContainer: string;
  onSecondaryFixed: string;
  onSecondaryFixedVariant: string;
  onSurface: string;
  onSurfaceInverse: string;
  onSurfaceVariant: string;
  onTertiary: string;
  onTertiaryContainer: string;
  onTertiaryFixed: string;
  onTertiaryFixedVariant: string;
  outline: string;
  outlineVariant: string;
  primaryContainer: string;
  primaryFixed: string;
  primaryFixedDim: string;
  primaryInverse: string;
  primarySurface: string;
  primaryVariant: string;
  secondary: string;
  secondaryContainer: string;
  secondaryFixed: string;
  secondaryFixedDim: string;
  secondaryVariant: string;
  surface: string;
  surfaceBright: string;
  surfaceContainer: string;
  surfaceContainerHigh: string;
  surfaceContainerHighest: string;
  surfaceContainerLow: string;
  surfaceContainerLowest: string;
  surfaceDim: string;
  surfaceInverse: string;
  surfaceVariant: string;
  tertiary: string;
  tertiaryContainer: string;
  tertiaryFixed: string;
  tertiaryFixedDim: string;
}

export interface Spec extends TurboModule {
  getColors: () => MaterialThemes;
}

export default TurboModuleRegistry.getEnforcing<Spec>('RTNColors');
