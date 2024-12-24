import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface ShowProps {
  title: string;
  message?: string;
  negativeButtonText?: string;
  positiveButtonText?: string;
  neutralButtonText?: string;
  cancelable?: boolean;
  headerAlignment?: string;
}

export interface Spec extends TurboModule {
  show: (
    props: ShowProps,
    onPositivePress?: () => void,
    onNegativePress?: () => void,
    onNeutralPress?: () => void,
    onCancel?: () => void
  ) => void;
}

export default TurboModuleRegistry.getEnforcing<Spec>('RTNAlertDialog');
