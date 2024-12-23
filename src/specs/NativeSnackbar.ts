import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface ShowProps {
  duration?: string;
  animationMode?: string;
  textColor?: string;
  text: string;
  textMaxLines?: number;
  actionText?: string;
  actionTextColor?: string;
  backgroundColor?: string;
}

export interface Spec extends TurboModule {
  show: (props: ShowProps, onActionPress?: () => void) => void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('RTNSnackbar');
