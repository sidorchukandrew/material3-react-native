import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface ShowProps {
  inputMode?: string;
  fullscreen?: boolean;
  value?: number;
  positiveButtonText?: string;
  negativeButtonText?: string;
  title?: string;
  maxDate?: number;
  minDate?: number;
  firstDayOfWeek?: number;
}

export interface Spec extends TurboModule {
  show: (
    props: ShowProps,
    onChange: (newValue: number) => void,
    onCancel?: () => void
  ) => Promise<void>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('RTNDatePicker');
